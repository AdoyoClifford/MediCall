package com.example.medicall

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

sealed class SignInEvent {
    data class SignIn(val email: String, val password: String) : SignInEvent()
    object SignUp: SignInEvent()
    object NavigateBack: SignInEvent()
}

@Composable
fun SignInScreen(auth: FirebaseAuth,navController: NavController,onNavigationEvent: (SignInEvent) -> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val snackbarErrorText = "Feature not available"
    val snackbarActionLabel = "dismiss"

    Scaffold(
        topBar = {
            SignInSignUpTopAppBar(
                onBackPressed = { onNavigationEvent(SignInEvent.NavigateBack) }
            )
        },
        content = { contentPadding ->
            SignInSignUpScreen(
                modifier = Modifier.supportWideScreen(),
                contentPadding = contentPadding,
                onSignUp = { onNavigationEvent(SignInEvent.SignUp) }
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SignInContent(auth = auth,
                        onSignInSubmitted = {email, password ->
                            onNavigationEvent(SignInEvent.SignIn(email, password))
                        }
                    ,orSignUp = {
                        navController.navigate(Screens.SignUpScreen.route)
                        }, navController = navController)
                }
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        ErrorSnackbar(
            snackbarHostState = snackbarHostState,
            onDismiss = { snackbarHostState.currentSnackbarData?.dismiss() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun SignInContent(navController: NavController,auth: FirebaseAuth,orSignUp: () -> Unit,
    onSignInSubmitted: (email: String, password: String) -> Unit
) {

//    val auth = FirebaseAuth.getInstance()
    Column (
        modifier = Modifier.fillMaxWidth()
            ){
        Text(
            text = "Hello again!",
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
        val focusRequester = remember {FocusRequester()}
        val emailState = remember {EmailState()}
        Email(emailState, onImeAction = {focusRequester.requestFocus()})

        Spacer(modifier = Modifier.height(18.dp))

        val passwordState = remember {PasswordState()}
        Password(label = "Password",
            passwordState, onImeAction = {focusRequester.requestFocus()}
        )

        Row (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
            Text(
                text = "Forgot password",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00B4FF),
                modifier = Modifier.clickable {  }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /*onSignInSubmitted(
            emailState.text, passwordState.text)*/
            Log.d("AUTH-APP",emailState.text + " -- "+passwordState.text)

            auth.signInWithEmailAndPassword(emailState.text, passwordState.text)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        navController.navigate(Screens.HomeScreen.route)
                    } else {
                        Log.d("AUTH-FIREBASE",task.exception.toString())
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithEmail:failure", task.exception)
                        //Toast.makeText(baseContext, "Authentication failed.",
                        //    Toast.LENGTH_SHORT).show()
                    }
                }
        } ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        enabled = emailState.isValid && passwordState.isValid,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF004763))) {
            Text("Sign In")
        }
       // SignInWith()
        Spacer(modifier = Modifier.height(16.dp))
        OrSignUp(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        , orSignUp = orSignUp)
    }
//    auth.createUserWithEmailAndPassword("email","password").addOnCompleteListener(){
//        task ->
//        if(task.isSuccessful){
//
//        }
//        else{
//            // handle failure
//        }
//
//    }

}

@Composable
fun ErrorSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = { }
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                content = {
                    Text(
                        text = data.message,
                        style = MaterialTheme.typography.body2
                    )
                },
                action = {
                    data.actionLabel?.let {
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = "dismiss",
                                color = Color.White
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}


