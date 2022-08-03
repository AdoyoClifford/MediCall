package com.example.medicall.contact_list

import com.example.medicall.data.Contacts

sealed class ContactListEvent {
    data class OnDeleteContact(val contact: Contacts) : ContactListEvent()
    data class OnDoneChange(val contact: Contacts, val isDone: Boolean): ContactListEvent()
    object OnUnDoDelete: ContactListEvent()
    data class OnContactClick(val contact: Contacts):ContactListEvent()
    object OnADdContactClick: ContactListEvent()
}