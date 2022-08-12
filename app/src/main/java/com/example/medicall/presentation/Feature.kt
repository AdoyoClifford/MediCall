package com.example.medicall.presentation

import android.graphics.Color
import androidx.annotation.DrawableRes
import com.example.medicall.R
import com.example.medicall.ui.theme.*

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: androidx.compose.ui.graphics.Color,
    val mediumColor: androidx.compose.ui.graphics.Color,
    val darkColor: androidx.compose.ui.graphics.Color
)

fun createFeatureList(): List<Feature> {
    val list = mutableListOf<Feature>()
    list.add(
        Feature(
            "Nearest doctors", R.drawable.ic_doctor, LightGreen1,
            LightGreen2,
            LightGreen3
        )
    )
    list.add(
        Feature(
            "Emergency Rescue Services", R.drawable.ic_ambulance, BlueViolet1,
            BlueViolet2,
            BlueViolet3
        )
    )
    list.add(
        Feature(
            "Emergency Contacts", R.drawable.ic_contacts, Beige1,
            Beige2,
            Beige3
        )
    )
    list.add(
        Feature(
            "Nearby Hospitals", R.drawable.ic_hospital, OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        )
    )

    return list

}