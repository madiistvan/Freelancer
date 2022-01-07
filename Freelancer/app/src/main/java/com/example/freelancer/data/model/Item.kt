package com.example.freelancer.data.model

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.freelancer.R
import com.example.freelancer.utils.ActiveUser
import com.example.freelancer.ui.theme.PrimaryColor
import com.example.freelancer.ui.theme.Secondary
import com.example.freelancer.ui.viewmodel.JobViewModel
import org.intellij.lang.annotations.JdkConstants

data class Item(
    val destination: String,
    val id: Int,
    val properties: String,
    val source: Source,
) : IItem {
    @Composable
    override fun ListViewItem(
        navController: NavController,
        item: IItem,
        onItemClicked: (item: IItem) -> Unit
    ) {
        Card(modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClicked(item)
                navController.navigate("itemsDetails")
            }
            .fillMaxWidth()
            .fillMaxHeight(0.75F),
            border = BorderStroke(4.dp, Secondary),
            shape = RoundedCornerShape(50.dp),
            elevation = Dp.Hairline,
            backgroundColor = PrimaryColor
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = (item as Item).source.owner.username,
                    textAlign = TextAlign.Center,
                    fontSize = 31.sp,
                    modifier = Modifier
                        .padding(25.dp)
                        .width(300.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(15.dp)
                )
                Row(Modifier.padding(0.dp,80.dp)) {
                    Image(
                        painter = painterResource(R.mipmap.appicon_foreground),
                        contentDescription = "box",
                    )
                    Image(
                        painter = painterResource(R.mipmap.box_foreground),
                        contentDescription = "box",
                    )
                    Image(
                        painter = painterResource(R.mipmap.appicon_foreground),
                        contentDescription = "box",
                    )
                }


            }

        }

    }

    @Composable
    override fun Details(
        item: IItem,
        navController: NavController?

    ) {
        val mod = Modifier
            .width(300.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(50.dp))
            .padding(15.dp)
        item as Item


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9F)
                .padding(20.dp),
            border = BorderStroke(
                4.dp,
                Secondary
            ),
            shape = RoundedCornerShape(50.dp),
            elevation = Dp.Hairline,
            backgroundColor = PrimaryColor
        )
        {
            Column(horizontalAlignment =Alignment.CenterHorizontally) {
                CustomText(title = "sender:", text = item.source.owner.username, mod = mod)
                CustomText(title = "source location:", text = item.source.location, mod = mod)
                CustomText(title = "properties:", text = item.properties, mod = mod)
                Spacer(modifier = Modifier.height(150.dp))
                Button(
                    onClick = {
                        val job = JobItem(ActiveUser.getActiveUser(), 0, this@Item, "??")
                        Log.d("item content", this@Item.destination)
                        val jobViewModel = JobViewModel()
                        jobViewModel.createJobs(this@Item)
                        navController?.navigate("Main")
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Secondary),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .height(50.dp),
                    shape = RoundedCornerShape(50.dp)


                ) {
                    Text(text = "Take job", color = Color.White)

                }
                Image(
                    painter = painterResource(R.mipmap.box_foreground),
                    contentDescription = "box",
                )
            }

        }

    }
}