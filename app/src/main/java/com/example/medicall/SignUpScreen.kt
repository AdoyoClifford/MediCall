package com.example.medicall

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

sealed class SignUpEvent {
    object SignIn : SignUpEvent()
    data class SignUp(val email: String, val password: String) : SignUpEvent()
    object SignInAsGuest : SignUpEvent()
    object NavigateBack : SignUpEvent()
}

@Composable
fun SignUpScreen(navController: NavController,auth: FirebaseAuth,onNavigationEvent: (SignUpEvent) -> Unit) {
    Scaffold(
        topBar = {
            SignInSignUpTopAppBar(
                onBackPressed = { onNavigationEvent(SignUpEvent.NavigateBack) }
            )
        },
        content = { contentPadding ->
            SignInSignUpScreen(
                onSignUp = { onNavigationEvent(SignUpEvent.SignInAsGuest) },
                contentPadding = contentPadding,
                modifier = Modifier.supportWideScreen()
            ) {
                Column {
                    SignUpContent(
                        onSignUpSubmitted = { email, password ->
                            onNavigationEvent(SignUpEvent.SignUp(email, password))
                        }
                    , auth = auth, navController = navController)
                }
            }
        }
    )
}

@Composable
fun SignUpContent(navController: NavController,auth: FirebaseAuth,
    onSignUpSubmitted: (email: String, password: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = "Hello!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)

        )
        Text(
            text = "We would like to know you",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )

        Spacer(modifier = Modifier.height(16.dp))
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }
        val emailState = remember { EmailState() }
        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))
        val passwordState = remember { PasswordState() }
        Password(
            label = "password",
            passwordState = passwordState,
            imeAction = ImeAction.Next,
            onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
            modifier = Modifier.focusRequester(passwordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }
        Password(
            label = "confirm password",
            passwordState = confirmPasswordState,
            onImeAction = { onSignUpSubmitted(emailState.text, passwordState.text) },
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "terms and conditions",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Medium,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {

                      auth.createUserWithEmailAndPassword(emailState.text,passwordState.text)
                          .addOnCompleteListener {
                              task ->
                              if(task.isSuccessful){
                                  navController.navigate(Screens.HomeScreen.route)

                                  // move to dashboard

                              }
                              else{
                                  Log.d("AUTH",task.exception.toString())
                              }
                          }
                      /*onSignUpSubmitted(emailState.text, passwordState.text)*/ },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                    passwordState.isValid && confirmPasswordState.isValid
        ) {
            Text(text = "create account")
        }
    }
}

//@Preview(widthDp = 1024)
//@Composable
//fun SignUpPreview() {
//    TroniksTheme {
//        SignUpScreen {}
//    }
//}
