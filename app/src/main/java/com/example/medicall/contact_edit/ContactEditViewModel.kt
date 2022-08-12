package com.example.medicall.contact_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicall.data.ContactRepository
import com.example.medicall.data.Contacts
import com.example.medicall.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactEditViewModel @Inject constructor(
    private val repository: ContactRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var contact by mutableStateOf<Contacts?>(null)
    private set

    var name by mutableStateOf("")
    private set

    var phone by mutableStateOf("")
    private set

    var email by mutableStateOf("")
    private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val contactId = savedStateHandle.get<Int>("contactId")!!
        if (contactId != -1) {
            viewModelScope.launch {
                repository.getContactById(contactId)?.let { contact ->
                    name = contact.name
                    phone = contact.phone ?: ""
                    this@ContactEditViewModel
                }
            }
        }
    }

    fun onEvent(event: AddContactEvent) {
        when(event) {
            is AddContactEvent.OnNameChange -> {
                name = event.name
            }
            is AddContactEvent.OnPhoneChange -> {
                phone = event.phone
            }
            is AddContactEvent.OnEmailChange -> {
                email = event.email
            }
            is AddContactEvent.Save -> {
                viewModelScope.launch {
                    if (name.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "The name can't be empty",
                            action = ""
                        ))
                        return@launch
                    }
                    repository.insertContact(
                        Contacts(
                            name = name,
                            phone = phone,
                            email = email,
                            isDone = contact?.isDone?: false,
                            id = contact?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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