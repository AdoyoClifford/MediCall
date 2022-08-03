package com.example.medicall.contact_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicall.Screens
import com.example.medicall.data.ContactRepository
import com.example.medicall.data.Contacts
import com.example.medicall.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val repository: ContactRepository
): ViewModel() {
    val contacts = repository.getContacts()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedContact: Contacts? = null

    fun onEvent(event: ContactListEvent) {
      when(event) {
          is ContactListEvent.OnContactClick -> {
              sendUiEvent(UiEvent.NavigateTo(Screens.ContactEdit.route + "contactId = ${event.contact.id}"))
          }
          is ContactListEvent.OnADdContactClick -> {
              sendUiEvent(UiEvent.NavigateTo(Screens.ContactEdit.route))
          }
          is ContactListEvent.OnDeleteContact -> {
              viewModelScope.launch {
                  deletedContact = event.contact
                  repository.deleteContact(event.contact)
                  sendUiEvent(UiEvent.ShowSnackBar(
                      message = "Contact deleted",
                      action = "Undo",
                  ))
              }

          }
          is ContactListEvent.OnUnDoDelete -> {
              deletedContact?.let {contact ->
                  viewModelScope.launch {
                      repository.insertContact(contact)
                  }
              }

          }
          is ContactListEvent.OnDoneChange -> {
              viewModelScope.launch {
                  repository.insertContact(
                      event.contact.copy(
                          isDone = event.isDone
                      )
                  )
              }

          }
      }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}