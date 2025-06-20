package com.example.life_renewed.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.life_renewed.R.drawable
import com.example.life_renewed.model.OnboardingItem
import com.example.life_renewed.navigation.NavScreens

class Onboarding {

    @Composable
    fun OnboardingScreen(navController : NavHostController) {
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { getOnboardingPages().size })
        val coroutineScope = rememberCoroutineScope()

        val onboardingItems = getOnboardingPages()

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            OnboardingPage(onboardingItems[page], navController)
        }


    }

    private fun getOnboardingPages(): List<OnboardingItem> {
        return listOf(
            OnboardingItem(
                title = "Groceries at Your Fingertips",
                description = "Find the favorites your store your want by your locations on neighborhood.",
                imageRes = drawable.ic_x_twitter_foreground
            ),
            OnboardingItem(
                title = "Fresh Delivered, Hassle Free",
                description = "Find the favorites your store your want by your locations on neighborhood.",
                imageRes = drawable.ic_youtube_foreground
            ),
            OnboardingItem(
                title = "Shop Smart. Eat Fresh",
                description = "Find the favorites your store your want by your locations on neighborhood.",
                imageRes = drawable.ic_facebook_foreground
            )
        )
    }

    @Composable
    fun OnboardingPage(page: OnboardingItem, navController: NavHostController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = page.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .height(350.dp)
                    .width(350.dp)
                    .padding(bottom = 20.dp)
            )
            Text(
                text = page.title, style = TextStyle(
                    fontFamily = FontFamily.Cursive,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = page.description,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            )
            if (page.title == "Shop Smart. Eat Fresh") {
                Text(
                    text = "Go Home",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp).align(Alignment.End).clickable {
                        navController.navigate(NavScreens.Home.route)
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Blue
                    )

                )
            }

        }

    }

    @Preview(showBackground = true)
    @Composable
    fun OnboardingScreenPreview() {
        OnboardingScreen(navController = NavHostController(context = LocalContext.current))
    }


}