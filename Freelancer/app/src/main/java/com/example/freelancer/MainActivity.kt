package com.example.freelancer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.freelancer.ui.screens.*
import com.example.freelancer.ui.screens.list.MainList
import com.example.freelancer.ui.theme.FreelancerTheme
import com.example.freelancer.ui.viewmodel.*
import com.example.freelancer.utils.ActiveUser
import com.sucho.placepicker.AddressData
import com.sucho.placepicker.Constants
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    companion object {
        lateinit var screen: String
        var location: String = ""
        var mapData = mutableMapOf("location" to "", "destination" to "")
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screen = intent.getStringExtra("screen")!!
        val viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {
            FreelancerTheme {
                // A surface container using the 'background' color from the theme
                //LoginScreen()
                Freelancer(this, this, this)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                val addressData = data?.getParcelableExtra<AddressData>(Constants.ADDRESS_INTENT)

                mapData["location"] = addressData.toString()



            }

        } else if (requestCode == 1) {
            val addressData = data?.getParcelableExtra<AddressData>(Constants.ADDRESS_INTENT)

            mapData["destination"] = addressData.toString()

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
        Log.d("PlacePicker location", mapData.get("location").toString())
        Log.d("PlacePicker destination", mapData.get("destination").toString())
    }


}

@ExperimentalFoundationApi
@Composable
fun Freelancer(context: Context, lifecycleOwner: LifecycleOwner, activity: MainActivity) {
    val registerViewModel = RegisterViewModel()
    val navController = rememberNavController()
    val userViewModel = UsersViewModel()
    val jobViewModel = JobViewModel()
    val loginViewModel = LoginViewModel()
    val itemViewModel = ItemViewModel()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }



    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Drawer(
                onDestinationClicked = { route ->
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {


        NavHost(navController = navController, startDestination = MainActivity.screen) {
            composable("Login") { LoginScreen(navController, loginViewModel) }
            composable("CreateJob") { CreateJobScreen(navController, activity) }
            composable("Register") { RegisterScreen(navController, registerViewModel) }
            composable("Main") { MainScreen(navController, openDrawer) }

            composable("UserList") {
                MainList(
                    navController = navController,
                    viewModel = userViewModel,
                    lifecycleOwner,
                    "Users"
                )
            }
            composable("userDetails") {
                userViewModel.clickedItem.Details(
                    item = userViewModel.clickedItem,
                    navController
                )
            }

            composable("jobDetails") {
                jobViewModel.clickedItem.Details(
                    item = jobViewModel.clickedItem,
                    navController
                )
            }
            composable("JobList") {
                MainList(
                    navController = navController,
                    viewModel = jobViewModel,
                    lifecycleOwner,
                    "Your Jobs"
                )
            }

            composable("itemsDetails") {
                itemViewModel.clickedItem.Details(
                    item = itemViewModel.clickedItem,
                    navController
                )
            }
            composable("Items") {
                MainList(
                    navController = navController,
                    viewModel = itemViewModel,
                    lifecycleOwner,
                    "Available Jobs"
                )
            }

            composable("SignOut") {
                val i = Intent(context, StartActivity::class.java)
                i.flags = FLAG_ACTIVITY_NEW_TASK
                ActiveUser.token = ""
                context.startActivity(i)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FreelancerTheme {
    }
}