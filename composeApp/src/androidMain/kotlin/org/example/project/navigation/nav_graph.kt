package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.Screens.appScreens.homeScreen
import org.example.project.Screens.authScreens.loginScreen
import org.example.project.Screens.authScreens.registerScreen

@Composable
fun App(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            loginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        composable("register"){
            registerScreen(
                onRegisterSuccess = {
                    navController.navigate("home"){
                        popUpTo("login") { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.navigate("login")
                }
            )
        }
        composable("home"){
            homeScreen()
        }
    }
}