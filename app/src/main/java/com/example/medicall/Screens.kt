package com.example.medicall

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash")
    object SignInScreen: Screens("login")
    object SignUpScreen: Screens("sign_in")
    object HomeScreen: Screens("home")
    object HomeScreen2: Screens("home2")
    object ProfileScreen: Screens("profile")
    object EmergencyScreen: Screens("emergency")
    object MapScreen: Screens("doctor")
    object ContactList: Screens("contact")
    object ContactEdit: Screens("contact_edit")

}