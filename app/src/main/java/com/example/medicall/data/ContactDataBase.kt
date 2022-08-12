package com.example.medicall.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Contacts::class],
    version = 2
)
abstract class ContactDataBase: RoomDatabase() {
    abstract val dao: ContactDao
}