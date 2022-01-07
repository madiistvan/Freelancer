package com.example.freelancer.utils

import android.app.Activity
import androidx.compose.material.IconButton
import androidx.core.app.ActivityCompat
import com.sucho.placepicker.Constants
import com.sucho.placepicker.MapType
import com.sucho.placepicker.PlacePicker

fun mapHelper(lat: Double,long:Double,activity:Activity){

        val intent = PlacePicker.IntentBuilder()
            .setLatLong(lat, long)  // Initial Latitude and Longitude the Map will load into
            .showLatLong(true)  // Show Coordinates in the Activity
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
            2,
            null
        )


}