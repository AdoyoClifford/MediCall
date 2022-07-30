package com.example.medicall

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicall.ui.HomeScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun Navigation() {
    val auth by lazy {
        Firebase.auth
    }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screens.SignInScreen.route) {
            SignInScreen(navController = navController, auth = auth) {}
        }
        composable(Screens.SignUpScreen.route) {
            SignUpScreen(auth = auth, navController = navController) {}
        }

        composable(Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
//        composable(route = Screens.MapScreen.route + "/{id}",
//            arguments = listOf(navArgument(name = "id"){
//                type = NavType.IntType
//            })) {entry ->
////            MapScreen(navController, entry.arguments?.getInt("id") ?: 0)
//            MapScreen(navController = navController, id = entry.arguments?.getInt("id"))
//        }
//
//        composable(Routes.TODO_LIST) {
//            ContactListScreen(
//                onNavigate = {
//                    navController.navigate(it.route)
//                }
//            )
//        }
//        composable(
//            route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
//            arguments = listOf(
//                navArgument(name = "todoId") {
//                    type = NavType.IntType
//                    defaultValue = -1
//                }
//            )
//        ) {
//            AddEditContactScreen(onPopBackStack = {
//                navController.popBackStack()
//            })
//        }
//    }
    }
}