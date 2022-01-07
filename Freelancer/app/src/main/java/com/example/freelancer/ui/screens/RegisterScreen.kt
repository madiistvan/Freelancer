package com.example.freelancer.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.freelancer.MainActivity
import com.example.freelancer.R
import com.example.freelancer.data.model.UserDTO
import com.example.freelancer.data.model.UserItem
import com.example.freelancer.ui.parts.elseButton
import com.example.freelancer.ui.parts.inputField
import com.example.freelancer.ui.parts.passwordField
import com.example.freelancer.ui.parts.title
import com.example.freelancer.ui.theme.PrimaryColor
import com.example.freelancer.ui.viewmodel.LoginViewModel
import com.example.freelancer.ui.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavHostController, registerViewModel: RegisterViewModel) {


    val email = remember { mutableStateOf(TextFieldValue()) }
    val usernameErrorState = remember { mutableStateOf(false) }

    val passwordErrorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordVisibility = remember { mutableStateOf(true) }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    registrationDialog(showDialog, setShowDialog)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {

        Image(
            painter = painterResource(R.mipmap.appicon_foreground),
            contentDescription = "box",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25F)
            ,
        )
        title("Register")

        inputField(email, usernameErrorState, "username")

        passwordField(
            password = password,
            passwordVisibility = passwordVisibility,
            passwordErrorState = passwordErrorState
        )

        val loginviewModel = LoginViewModel()
        Button(
            onClick = {
                when {
                    email.value.text.isEmpty() -> {
                        usernameErrorState.value = true
                    }
                    password.value.text.isEmpty() -> {
                        passwordErrorState.value = true
                    }
                    else -> {
                        passwordErrorState.value = false
                        usernameErrorState.value = false
                        if (registerViewModel.registerUser(
                                UserItem(
                                    0,
                                    password.value.text,
                                    "tmp",
                                    0,
                                    email.value.text
                                ),
                                onFailure = { setShowDialog(true) },
                                onSuccess = {
                                    Log.d("navButton", "Registration successful")
                                    loginviewModel.loginUser(
                                        UserDTO(

                                            email.value.text,
                                            password.value.text,
                                        ), { setShowDialog(true) }, {
                                            MainActivity.screen = "Main"
                                            navController.navigate("Main")
                                        })
                                }
                            )
                        ) {

                        } else {
                            setShowDialog(true)
                        }

                    }
                }
            },
            content = {
                Text(text = "Register", color = Color.White)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
            shape = RoundedCornerShape(50.dp),

            )
        Spacer(Modifier.size(16.dp))
        elseButton(text = "Login?", navController = navController)

    }

}

@Composable
fun registrationDialog(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(text = "Registration faild", color = PrimaryColor)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = PrimaryColor,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .height(50.dp)
                ) {
                    Text(text = "Try again", color = PrimaryColor)
                }
            },
            text = {
                Text(text = "Something went wrong", color = PrimaryColor)
            },

            )
    }
}


