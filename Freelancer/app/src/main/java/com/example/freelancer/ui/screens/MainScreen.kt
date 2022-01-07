package com.example.freelancer.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.freelancer.R
import com.example.freelancer.ui.theme.PrimaryColor
import com.example.freelancer.ui.theme.PrimaryVariant
import com.example.freelancer.ui.theme.Secondary
import kotlinx.coroutines.Job
import java.util.concurrent.ThreadLocalRandom

object Colors {
    var colors = ArrayList<Color>()
    fun initColors() {
        for (i in 0..3) {
            colors.add(rndColor())
        }
    }


}

@Composable
fun MainScreen(navController: NavController, openDrawer: () -> Job) {


    TopBar(onButtonClicked = { openDrawer() })
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            GridItem(
                navController = navController,
                navTo = "Items",
                title = "Get a job!",
                PrimaryColor,
                Secondary
            )
            GridItem(
                navController = navController,
                navTo = "JobList",
                title = "View your jobs!",
                Secondary,
                PrimaryColor
            )

        }
        Row() {
            GridItem(
                navController = navController,
                navTo = "CreateJob",
                title = "Post a job!",
                Secondary,
                PrimaryColor
            )
            GridItem(
                navController = navController,
                navTo = "UserList",
                title = "View other users",
                PrimaryColor,
                Secondary
            )

        }
    }


}


@Composable
fun GridItem(navController: NavController, navTo: String, title: String, color1: Color,color2:Color) {

    val infiniteTransition = rememberInfiniteTransition()
    val size by infiniteTransition.animateFloat(
        initialValue = 180f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                800,
                delayMillis = ThreadLocalRandom.current().nextInt(150, 350),
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(modifier = Modifier
        .padding(8.dp)
        .clickable {
            navController.navigate(navTo)
        }
        .width(Dp(size))
        .height(Dp(size))
        ,
        border = BorderStroke(2.dp, color2),
        shape = RoundedCornerShape(50.dp),
        elevation = Dp.Hairline, backgroundColor = color1
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 31.sp,
            modifier = Modifier
                .padding(15.dp), color = Color.White
        )
    }
}

fun rndColor(): Color {
    return Color(
        10 * ThreadLocalRandom.current().nextInt(20, 26),
        ThreadLocalRandom.current().nextInt(25, 60),
        ThreadLocalRandom.current().nextInt(25, 60),
        ThreadLocalRandom.current().nextInt(100, 255)
    )
}


@Composable
fun TopBar(title: String = "Freelancer", onButtonClicked: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        backgroundColor = PrimaryVariant,
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                val icon = Icons.Default.Menu
                Icon(icon, "Menu")
            }
        },
        actions = {
            Image(painterResource(R.mipmap.appicon_foreground),"",Modifier.alpha(100F))
        }
    )
}

sealed class DrawerScreens(val title: String, val route: String, val id: Int) {
    object SignOut : DrawerScreens("SignOut", "SignOut", R.mipmap.logout_foreground)
    object Jobs : DrawerScreens("Jobs", "JobList", R.mipmap.workerhat_foreground)
    object Users :
        DrawerScreens("Users", "UserList", R.drawable.ic_baseline_sentiment_satisfied_alt_24)

    object CreateJobs : DrawerScreens("Create Jobs", "CreateJob", R.mipmap.box_foreground)
    object Items : DrawerScreens("Available jobs", "Items", R.drawable.ic_baseline_work_24)

}

private val screens = listOf(
    DrawerScreens.SignOut,
    DrawerScreens.Users,
    DrawerScreens.Jobs,
    DrawerScreens.CreateJobs,
    DrawerScreens.Items
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier.background(PrimaryVariant),
    onDestinationClicked: (route: String) -> Unit
) {
    Scaffold() {
        Column(
            modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 48.dp)
        ) {
            Image(
                painter = painterResource(R.mipmap.appicon_foreground),
                contentDescription = "App icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            screens.forEach { screen ->
                Card(
                    border = BorderStroke(2.dp, PrimaryColor),
                    backgroundColor = Secondary
                ) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = screen.title,
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.clickable {
                                onDestinationClicked(screen.route)
                            },
                        )
                        Spacer(Modifier.width(30.dp))
                        Image(
                            painter = painterResource(screen.id),
                            contentDescription = "icon",
                            modifier = Modifier
                                .height(40.dp)
                                .wrapContentSize(align = Alignment.BottomEnd)
                        )
                    }
                }

            }
        }
    }

}
