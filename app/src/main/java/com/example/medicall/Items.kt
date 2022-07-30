package com.example.medicall

import androidx.annotation.DrawableRes

data class Items(
    @DrawableRes val image: Int,
    val name: String
)

fun createDataList(): List<Items> {
    val list = mutableListOf<Items>()
    list.add(Items(R.drawable.apple, name = "Apple"))
    list.add(Items(R.drawable.facebook, name = "Facebook"))
    list.add(Items(R.drawable.google, name = "Google"))

    return list
}
