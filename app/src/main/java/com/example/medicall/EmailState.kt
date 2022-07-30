package com.example.medicall

//email is valid if there is some text before and after "@"
private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

class EmailState: TextFieldState(
    validator = ::isEmailValid, errorFor = ::emailValidationError
)

//Validate email pattern
private fun isEmailValid(email: String): Boolean {
    return email.matches(EMAIL_VALIDATION_REGEX.toRegex())
}
//Error message for email pattern
private fun emailValidationError(email: String): String {
    return if (isEmailValid(email)) {
        ""
    } else {
        "Invalid email"
    }
}
