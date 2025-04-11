package org.example.project.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.example.project.Activities.MainActivity
import org.example.project.AuthScreens.loginScreen
import org.example.project.AuthServiceFactory


@Composable
fun AuthNavHost(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val authService = remember { AuthServiceFactory().create() }
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            loginScreen(
                onLoginClick = {email, password ->
                    scope.launch{
                        val success = authService.login(email,password)
                        if (success)
                        {
                            val intent = Intent(context,MainActivity::class.java)
                            context.startActivity(intent)
                            (context as? Activity)?.finish()
                        }
                        else{
                            println("Login failed")
                        }
                    }
                },
                onRegisterPageClick = {
                    navController.navigate("register")
                }
            )
        }
    }
}
