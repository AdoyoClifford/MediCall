package com.example.medicall.presentation

import android.graphics.Color
import androidx.annotation.DrawableRes
import com.example.medicall.R
import com.example.medicall.Screens
import com.example.medicall.ui.theme.*

data class Feature(
    val id: Int,
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: androidx.compose.ui.graphics.Color,
    val mediumColor: androidx.compose.ui.graphics.Color,
    val darkColor: androidx.compose.ui.graphics.Color,
    val route: String
)

fun createFeatureList(): List<Feature> {
    val list = mutableListOf<Feature>()
    list.add(
        Feature(
            1,
            "Nearest doctors", R.drawable.ic_doctor, LightGreen1,
            LightGreen2,
            LightGreen3,
            Screens.Doctors.route
        )
    )
    list.add(
        Feature(
            2,
            "Emergency Rescue Services", R.drawable.ic_ambulance, BlueViolet1,
            BlueViolet2,
            BlueViolet3,
            Screens.Ambulances.route
        )
    )
    list.add(
        Feature(
            3,
            "Emergency Contacts", R.drawable.ic_contacts, Beige1,
            Beige2,
            Beige3,
            Screens.ContactList.route
        )
    )
    list.add(
        Feature(
            4,
            "Nearby Hospitals", R.drawable.ic_hospital, OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3,
            Screens.Hospital.route
        )
    )

    return list

}