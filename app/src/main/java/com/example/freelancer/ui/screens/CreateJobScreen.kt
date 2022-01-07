package com.example.freelancer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.freelancer.utils.ActiveUser
import com.example.freelancer.MainActivity
import com.example.freelancer.R
import com.example.freelancer.data.model.Source
import com.example.freelancer.ui.parts.MapField
import com.example.freelancer.ui.parts.inputField
import com.example.freelancer.ui.parts.title
import com.example.freelancer.ui.theme.PrimaryColor
import com.example.freelancer.ui.viewmodel.SourceViewModel

@Composable
fun CreateJobScreen(navController: NavHostController,activity: MainActivity) {
    val destination = remember { mutableStateOf(TextFieldValue()) }
    val destinationErrorState = remember { mutableStateOf(false) }

    val sourcelocation = remember { mutableStateOf(TextFieldValue()) }
    val sourcelocationErrorState = remember { mutableStateOf(false) }

    val properties = remember { mutableStateOf(TextFieldValue()) }
    val propertiesErrorState = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.mipmap.box_foreground),
            contentDescription = "box",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25F)
                ,
        )
        sourcelocation.value = TextFieldValue(MainActivity.mapData["location"] ?:"")
        destination.value = TextFieldValue(MainActivity.mapData["destination"] ?:"")

        title(title = "Create Job")
        MapField(input = destination, ErrorState =destinationErrorState , text = "Destination",activity)
        MapField(input = sourcelocation, ErrorState =sourcelocationErrorState , text = "Location",activity)
        inputField(input = properties, ErrorState =propertiesErrorState , text = "Properties" )

        CreateJobButton(destinationErrorState,destination,sourcelocation,sourcelocationErrorState,properties,propertiesErrorState,"Create",navController)

        

    }
}
@Composable
fun CreateJobButton(
    destinationErrorState: MutableState<Boolean>,
    destination: MutableState<TextFieldValue>,
    sourcelocation: MutableState<TextFieldValue>,
    sourcelocationErrorState: MutableState<Boolean>,
    properties: MutableState<TextFieldValue>,
    propertiesErrorState: MutableState<Boolean>,
    text: String,
    navController: NavHostController
) {

    Button(
        onClick = {
            when {
                destination.value.text.isEmpty() -> {
                    destinationErrorState.value = true
                }
                sourcelocation.value.text.isEmpty() -> {
                    sourcelocationErrorState.value = true
                }
                properties.value.text.isEmpty() -> {
                    propertiesErrorState.value = true
                }
                else -> {
                    destinationErrorState.value = false
                    propertiesErrorState.value = false
                    propertiesErrorState.value = false

                    val source = Source(id = 5,
                        location = sourcelocation.value.text,
                        ActiveUser.getActiveUser()
                    )

                    MainActivity.mapData["location"]=""
                    MainActivity.mapData["destination"]=""
                    val sourceViewModel = SourceViewModel()
                    sourceViewModel.createSource(source = source,destination.value.text,properties.value.text)
                    navController.navigate("Main")




                }
            }
        },
        content = {
            Text(text = text, color = Color.White)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
        shape = RoundedCornerShape(50.dp)
    )
    Spacer(Modifier.size(16.dp))

}
