package com.example.medicall.contact_list

import androidx.lifecycle.ViewModel
import com.example.medicall.data.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val repository: ContactRepository
): ViewModel() {
}