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
import com.example.freelancer.data.network.FreelancerApiClient
import com.example.freelancer.ui.theme.PrimaryColor
import com.example.freelancer.ui.theme.Secondary
import com.example.freelancer.utils.RepositoryService

data class JobItem(
    val freelancer: UserItem,
    val id: Int,
    val item: Item,
    val status: String
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
                navController.navigate("jobDetails")
            }
            .padding(10.dp, 50.dp, 10.dp, 0.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.75F),
            border = BorderStroke(4.dp, Secondary),
            shape = RoundedCornerShape(50.dp),
            elevation = Dp.Hairline,
            backgroundColor = PrimaryColor
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = (item as JobItem).item.destination,
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

                }
                Row(Modifier.padding(0.dp,100.dp)) {
                    Image(
                        painter = painterResource(R.mipmap.workerhat_foreground),
                        contentDescription = "",
                    )
                    Image(
                        painter = painterResource(R.mipmap.appicon_foreground),
                        contentDescription = "",
                    )
                    Image(
                        painter = painterResource(R.mipmap.workerhat_foreground),
                        contentDescription = "",
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
            .padding(0.dp)
            .width(300.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(50.dp))
            .padding(10.dp)

        item as JobItem
        Card(
            modifier = Modifier
                .padding( 10.dp, 10.dp, 10.dp, 0.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.9F),
            border = BorderStroke(4.dp, Secondary),
            shape = RoundedCornerShape(50.dp),
            elevation = Dp.Hairline,
            backgroundColor = PrimaryColor
        )
        {
            Column() {
                CustomText(title = "username:", text = item.freelancer.username, mod = mod)
                CustomText(title = "properties:", text = item.item.properties, mod = mod)
                CustomText(title = "destination:", text = item.item.destination, mod = mod)
                CustomText(title = "source location:", text = item.item.source.location, mod = mod)


                Spacer(modifier = Modifier.height(100.dp))

                Button(
                    onClick = {
                        val repo = RepositoryService.getJobRepository()
                        repo.delivereJob(this@JobItem) {
                            if (it?.id != null) {
                                Log.d("Job delivery", "success ")

                                navController?.navigate("Main")
                            } else {
                                Log.d("Job delivery", "failure ")
                            }
                        }
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
                    Text(text = "Delivered", color = Color.White)

                }
                Button(
                    onClick = {
                        val repo =RepositoryService.getJobRepository()
                        repo.passJob(this@JobItem) {
                            if (it?.id != null) {
                                Log.d("Job Passing", "success ")

                                navController?.navigate("Main")
                            } else {
                                Log.d("Job Passing", "failure ")
                            }
                        }
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
                    Text(text = "Pass job", color = Color.White)

                }
            }
        }
    }
}