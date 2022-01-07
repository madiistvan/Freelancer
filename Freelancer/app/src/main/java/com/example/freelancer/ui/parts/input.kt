package com.example.freelancer.ui.parts

import android.app.Activity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.freelancer.MainActivity
import com.example.freelancer.ui.theme.PrimaryColor
import com.example.freelancer.ui.theme.Secondary
import com.sucho.placepicker.Constants
import com.sucho.placepicker.MapType
import com.sucho.placepicker.PlacePicker
import kotlin.reflect.KProperty1

@Composable
fun inputField(input: MutableState<TextFieldValue>, ErrorState: MutableState<Boolean>,text: String) {

    OutlinedTextField(
        value = input.value,
        onValueChange = {
            if (ErrorState.value) {
                ErrorState.value = false
            }
            input.value = it
        },
        isError = ErrorState.value,
        modifier = Modifier.fillMaxWidth().height(60.dp),
        label = {
            Text(text = text)
        },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor
        )
    )
    if (ErrorState.value) {
        Text(text = "Required", color = PrimaryColor)
    }
    Spacer(Modifier.size(16.dp))
}
@Composable
fun MapField(
    input: MutableState<TextFieldValue>, ErrorState: MutableState<Boolean>,
    text: String,
    activity: Activity
) {

    OutlinedTextField(
        value = input.value,
        onValueChange = {
            if (ErrorState.value) {
                ErrorState.value = false
            }
            input.value = it
        },
        isError = ErrorState.value,
        modifier = Modifier.fillMaxWidth().height(60.dp),
        label = {
            Text(text = text)
        },
        trailingIcon = {
            IconButton(onClick = {
                val intent = PlacePicker.IntentBuilder()
                    .setLatLong(40.748672, -73.985628)  // Initial Latitude and Longitude the Map will load into
                    .showLatLong(false)  // Show Coordinates in the Activity
                    .setMapZoom(12.0f)  // Map Zoom Level. Default: 14.0
                    .setAddressRequired(true) // Set If return only Coordinates if cannot fetch Address for the coordinates. Default: True
                    .hideMarkerShadow(true) // Hides the shadow under the map marker. Default: False
                    //.setMarkerDrawable(R.drawable.marker) // Change the default Marker Image
                    //.setMarkerImageImageColor(R.color.colorPrimary)
                    //.setFabColor(R.color.fabColor)
                    //.setPrimaryTextColor(R.color.primaryTextColor) // Change text color of Shortened Address
                    //.setSecondaryTextColor(R.color.secondaryTextColor) // Change text color of full Address
                    //.setBottomViewColor(R.color.bottomViewColor) // Change Address View Background Color (Default: White)
                    //.setMapRawResourceStyle(R.raw.map_style)  //Set Map Style (https://mapstyle.withgoogle.com/)
                    .setMapType(MapType.NORMAL)
                    .setPlaceSearchBar(false, Constants.GOOGLE_API_KEY) //Activate GooglePlace Search Bar. Default is false/not activated. SearchBar is a chargeable feature by Google
                    .onlyCoordinates(false)  //Get only Coordinates from Place Picker
                    .hideLocationButton(true)   //Hide Location Button (Default: false)
                    .disableMarkerAnimation(true)   //Disable Marker Animation (Default: false)
                    .build(activity)
                ActivityCompat.startActivityForResult(
                    activity,
                    intent,
                    if(text.lowercase() =="location") 0 else 1,
                    null
                )

            },
            ) {
                Icon(
                    Icons.Default.Map,
                    "Map",
                    tint = Secondary
                )
            }
        },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor
        )
    )
    if (ErrorState.value) {
        Text(text = "Required", color = PrimaryColor)
    }
    Spacer(Modifier.size(16.dp))
}

@Composable
fun passwordField(
    password: MutableState<TextFieldValue>,
    passwordVisibility: MutableState<Boolean>,
    passwordErrorState: MutableState<Boolean>
) {

    OutlinedTextField(
        value = password.value,
        onValueChange = {
            if (passwordErrorState.value) {
                passwordErrorState.value = false
            }
            password.value = it
        },
        isError = passwordErrorState.value,
        modifier = Modifier.fillMaxWidth().height(60.dp),
        label = {
            Text(text = "Password")
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility.value = !passwordVisibility.value
            },
            ) {
                Icon(
                    if (passwordVisibility.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    "Visibility",
                    tint = PrimaryColor
                )
            }
        },
        shape = RoundedCornerShape(50.dp),
        visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor
        )
    )
    if (passwordErrorState.value) {
        Text(text = "Required", color =PrimaryColor)
    }
    Spacer(Modifier.size(16.dp))
}

@Composable
fun title(title: String){
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = PrimaryColor)) {
            append(title)
        }

    }, fontSize = 30.sp)
    Spacer(Modifier.size(16.dp))

}

