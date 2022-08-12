package com.example.medicall

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash")
    object SignInScreen: Screens("login")
    object SignUpScreen: Screens("sign_in")
    object HomeScreen: Screens("home")
    object ProfileScreen: Screens("profile")
    object EmergencyScreen: Screens("emergency")
    object MapScreen: Screens("doctor")
    object ContactList: Screens("contact")
    object Doctors: Screens("doctor")
    object Ambulances: Screens("ambulances")
    object Hospital: Screens("hospital")
    object ContactEdit: Screens("contact_edit")


}