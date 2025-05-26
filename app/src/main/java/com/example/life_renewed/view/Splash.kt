package com.example.life_renewed.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

class Splash {

    @Composable
    fun SplashScreen(navController: NavHostController, modifier: Modifier) {

        Column {
            Text("Splash Screen")

        }
    }
}