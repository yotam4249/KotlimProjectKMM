package org.example.project.Screens.authScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.example.project.auth.AuthService
import org.example.project.auth.AuthServiceFactory
import org.w3c.dom.Text

@Composable

fun registerScreen(
    onBackToLogin : () -> Unit,
    onRegisterSuccess : () -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var mainError by remember { mutableStateOf<String?>(null) }

    val isInPreview = LocalInspectionMode.current
    val authService: AuthService? = remember {
        if(!isInPreview) AuthServiceFactory().createAuthService() else null
    }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement =Arrangement.Center
    ) {
        Text("Register Page", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            label = { Text("Email:") },
            modifier = Modifier.fillMaxWidth()
        )
        if (emailError) {
            Text("Email is required", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            label = { Text("Password:") },
            modifier = Modifier.fillMaxWidth()
        )
        if (passwordError) {
            Text("Password is required", color = Color.Red, fontSize = 12.sp)
        }

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            label = { Text("Name:") },
            modifier = Modifier.fillMaxWidth()
        )
        if (nameError) {
            Text("Name is required", color = Color.Red, fontSize = 12.sp)
        }


        Button(onClick = {
            emailError = email.isBlank()
            passwordError = password.isBlank()
            nameError = name.isBlank()

            if(emailError || passwordError || nameError) return@Button

            if(!isInPreview && authService != null){
                isLoading = true
                mainError = null

                coroutineScope.launch {
                    val res = authService.register(email,password)
                    if(res.success){
                        onRegisterSuccess()
                    }else{
                        mainError = res.errorMessage ?: "Registration failed"
                    }
                }
            }
        },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 8.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("Login")
            }
        }



    }
}

@Preview(showBackground = true, name = "Register Screen Preview")
@Composable
fun RegisterScreen(){
    MaterialTheme{
        registerScreen(
            onBackToLogin = {},
            onRegisterSuccess = {}
        )
    }
}
