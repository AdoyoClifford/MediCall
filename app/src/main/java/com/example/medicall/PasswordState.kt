package com.example.medicall


//Regex pattern for password with letters and numbers
const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+-=]).{8,}$"

class PasswordState :
    TextFieldState(validator = ::isPasswordValid, errorFor = ::passwordValidationError)

class ConfirmPasswordState(private val passwordState: PasswordState) : TextFieldState() {
    override val isValid
        get() = passwordAndConfirmationValid(passwordState.text, text)

    override fun getError(): String? {
        return if (showErrors()) {
            passwordConfirmationError()
        } else {
            null
        }
    }
}

private fun passwordAndConfirmationValid(password: String, confirmedPassword: String): Boolean {
    return isPasswordValid(password) && password == confirmedPassword
}

//Check the password length is valid
private fun isPasswordValid(password: String): Boolean {


    return password.length > 8 && password.matches(PASSWORD_PATTERN.toRegex())
}

//check if password contains all the requirements
@Suppress("UNUSED_PARAMETER")
private fun passwordValidationError(password: String): String {

    return "Password must contain at least 3 of the following: numbers, lowercase letters, uppercase letters, and special characters"
}

private fun passwordConfirmationError(): String {
    return "Passwords don't match"
}

