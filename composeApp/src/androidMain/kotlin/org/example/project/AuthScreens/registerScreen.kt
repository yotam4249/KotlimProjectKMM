package org.example.project.AuthScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable

fun registerScreen(
    onRegisterClick : () -> Unit,
    onGoToLoginClick : () -> Unit
){
    Column {
        Text("Register Page")

        Button(onClick = onRegisterClick) {
            Text("Registered")
        }

        Button(onClick = onGoToLoginClick) {
            Text("Go to login")
        }
    }

}