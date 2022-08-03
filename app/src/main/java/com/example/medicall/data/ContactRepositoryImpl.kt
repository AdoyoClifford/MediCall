package com.example.medicall.data

import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(
    private val dao: ContactDao
): ContactRepository{
    override suspend fun insertContact(contacts: Contacts) {
        dao.insertContact(contacts)
    }

    override suspend fun deleteContact(contacts: Contacts) {
        dao.deleteContact(contacts)
    }

    override suspend fun getContactById(id: Int): Contacts? {
        return dao.getContactById(id)
    }

    override fun getContacts(): Flow<List<Contacts>> {
        return dao.getContacts()
    }
}