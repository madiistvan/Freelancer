package com.example.freelancer.ui.screens

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.unit.dp
import com.example.freelancer.MainActivity
import com.example.freelancer.ui.theme.PrimaryColor
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.material.Divider
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.freelancer.R


class ComposeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.padding(0.dp,0.dp,0.dp,100.dp)) {
            Image(
                painter = painterResource(R.mipmap.appicon_foreground),
                contentDescription = "App icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
            )
            Button(
                onClick = {
                    val i = Intent(context, MainActivity::class.java)
                    i.putExtra("screen", "Login")
                    context.startActivity(i)
                },
                content = {
                    Text(text = "Login", color = Color.White)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
                shape = RoundedCornerShape(50.dp)
            )
            Spacer(Modifier.size(16.dp))

            Button(
                onClick = {
                    val i = Intent(context, MainActivity::class.java)
                    i.putExtra("screen", "Register")
                    context.startActivity(i)

                },
                content = {
                    Text(text = "Register", color = Color.White)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
                shape = RoundedCornerShape(50.dp)
            )
        }

    }
}