package com.example.medicall.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contacts(
    val name: String,
    val phone: Int,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
