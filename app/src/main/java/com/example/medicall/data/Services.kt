package com.example.medicall.data

import androidx.annotation.DrawableRes
import com.example.medicall.R

data class Services(
    val id: Int,
    @DrawableRes val image: Int,
    val name: String,
)

fun createDataList(): List<Services> {
    val list = mutableListOf<Services>()
    list.add(Services(1, R.drawable.hospital, "Hospital"))
    list.add(Services(2, R.drawable.doctor, "Doctor"))
    list.add(Services(3, R.drawable.ambulance, "Ambulance"))

    return list
}
