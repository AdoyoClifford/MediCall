package com.example.medicall.data

import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun insertContact(contacts: Contacts)

    suspend fun deleteContact(contacts: Contacts)

    suspend fun getContactById(id: Int): Contacts?

    fun getContacts(): Flow<List<Contacts>>
}