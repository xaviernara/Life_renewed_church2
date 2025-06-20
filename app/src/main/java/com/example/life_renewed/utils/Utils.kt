package com.example.life_renewed.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.life_renewed.R
import com.example.life_renewed.navigation.NavScreens

class Utils {

    companion object {

        @Composable
        fun TopBanner(drawable: Int = R.drawable.ic_launcher_background) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = drawable),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                    // Or .FillBounds, .Fit, etc.
                    // ContentScale.Crop will fill bounds and crop if necessary
                    // ContentScale.FillBounds will stretch
                )
            }
        }
    }

    @Composable
    fun showScaffold(navController: NavHostController): Boolean {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        return navBackStackEntry.value?.destination?.route != NavScreens.Splash.route
    }


}