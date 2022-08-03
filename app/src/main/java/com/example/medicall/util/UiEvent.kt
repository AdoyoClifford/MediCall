package com.example.medicall.util

sealed class UiEvent{
    object PopBackStack: UiEvent()
    data class NavigateTo(val route: String): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String
    ): UiEvent()

}
