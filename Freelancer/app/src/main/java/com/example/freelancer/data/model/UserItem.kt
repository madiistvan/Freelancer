package com.example.freelancer.data.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.freelancer.ui.screens.rndColor
import com.example.freelancer.ui.theme.PrimaryColor
import com.example.freelancer.ui.theme.Secondary

data class UserItem(
    val id: Int,
    val password: String,
    val role: String,
    val score: Int,
    val username: String
):IItem {
    @Composable
    override fun ListViewItem(
        navController: NavController,
        iItem: IItem,
        onItemClicked: (item: IItem) -> Unit
    ) {
        Card(modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClicked(iItem)
                navController.navigate("userDetails")
            }
            .padding(10.dp, 50.dp, 10.dp, 0.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.75F)
            ,
            border = BorderStroke(4.dp, Secondary),
            shape = RoundedCornerShape(50.dp),
            elevation = Dp.Hairline,
            backgroundColor = PrimaryColor
        )
        {
            Column() {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = (iItem as UserItem ).username,
                        textAlign = TextAlign.Center,
                        fontSize = 31.sp,
                        modifier = Modifier
                            .padding(25.dp)
                            .offset(
                                x = 2.dp,
                                y = 2.dp
                            )
                            .width(300.dp)
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(15.dp)
                    )

                }
                Spacer(modifier = Modifier.height(75.dp))
                Row() {
                    Image(
                        painter = painterResource(R.mipmap.workerhat_foreground),
                        contentDescription = "workerhat"
                    )
                    Image(
                        painter = painterResource(R.mipmap.box_foreground),
                        contentDescription = "workerhat"
                    )
                    Image(
                        painter = painterResource(R.mipmap.workerhat_foreground),
                        contentDescription = "workerhat"
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
            .padding(20.dp)
        item as UserItem


            Card(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9F)
                .padding(10.dp)
                ,
                border = BorderStroke(4.dp, Secondary),
                shape = RoundedCornerShape(50.dp),
                elevation = Dp.Hairline,
                backgroundColor = PrimaryColor
            )
            {
                Column() {
                    CustomText(title = "username:", text =item.username , mod = mod )
                    CustomText(title = "role:", text =item.role , mod = mod )
                    CustomText(title = "score:", text =item.score.toString() , mod = mod )
                }
            }


    }
}


@Composable
fun CustomText(title:String,text:String,mod:Modifier){

    Column(modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth()) {
        Text(text=title,modifier = Modifier.padding(start = 20.dp),color = Color.White)
        Text(text = text,modifier = mod,textAlign = TextAlign.Center,
            fontSize = 15.sp,color = Color.White)
    }
    Spacer(Modifier.size(5.dp))


}