package com.example.life_renewed.view

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.life_renewed.R
import com.example.life_renewed.navigation.NavScreens
import com.example.life_renewed.ui.theme.Purple40
import kotlinx.coroutines.delay

class Splash {

    @Composable
    fun SplashScreen(navController: NavHostController, modifier: Modifier = Modifier) {

        var showSplash by remember { mutableStateOf(false) }
        val alpha = animateFloatAsState(
            targetValue = if (showSplash) 1f else 0f,
            animationSpec = tween(durationMillis = 3000)
        )

        LaunchedEffect(key1 = true) {
            showSplash = true
            delay(3000L)
            navController.navigate(NavScreens.Onboarding.route)
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
//                .background(if (isSystemInDarkTheme()) Color.Black else Purple40)
                .background(Purple40)
                .fillMaxSize()

        ) {
            Column(
                modifier = modifier.padding(horizontal = 16.dp).alpha(alpha.value)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_x_twitter_foreground),
                    contentDescription = "logo",
                    modifier = Modifier.size(50.dp)
                )
                HorizontalDivider()
                Text(
                    text = stringResource(R.string.church_name),
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )

            }

        }
    }

    @Preview
    @Composable
    fun SplashScreenPreview() {
        SplashScreen(navController = NavHostController(LocalContext.current))

    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun SplashScreenDarkPreview() {
        SplashScreen(navController = NavHostController(LocalContext.current))

    }
}