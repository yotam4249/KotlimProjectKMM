package org.example.project.Screens.appScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun homeScreen()
{
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("🎉 Welcome Home!")
    }
}