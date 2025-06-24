package com.example.life_renewed.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.life_renewed.R
import com.example.life_renewed.R.drawable
import com.example.life_renewed.model.OnboardingItem
import com.example.life_renewed.navigation.NavScreens
import kotlinx.coroutines.launch

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
//                Text(
//                    text = "Go Home",
//                    modifier = Modifier
//                        .padding(horizontal = 16.dp, vertical = 10.dp)
//                        .align(Alignment.End)
//                        .clickable {
//                            navController.navigate(NavScreens.Home.route)
//                        },
//                    style = TextStyle(
//                        fontFamily = FontFamily.Default,
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.W400,
//                        color = Color.Blue
//                    )
//
//                )

                LoginBottomSheet(navController = navController)
            }

        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginBottomSheet(navController: NavHostController){
        val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
        val coroutineScope = rememberCoroutineScope()
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            },
            sheetState = bottomSheetState,
            modifier = Modifier.padding(bottom = 16.dp),
            scrimColor = Color.LightGray.copy(alpha = 0.5f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = stringResource(R.string.welcome_to_life_renewed_harvest_ministries),
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.please_sign_in),
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )

                HorizontalDivider()

                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            navController.navigate(NavScreens.Home.route)
                        }
                    },
                ) {
                    Text(text = "Login")
                }
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun LoginBottomSheetPreview() {
        LoginBottomSheet(navController = NavHostController(context = LocalContext.current))
    }

    @Preview(showBackground = true)
    @Composable
    fun OnboardingScreenPreview() {
        OnboardingScreen(navController = NavHostController(context = LocalContext.current))
    }


}