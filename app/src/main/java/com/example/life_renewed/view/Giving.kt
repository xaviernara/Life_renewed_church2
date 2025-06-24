package com.example.life_renewed.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.example.life_renewed.R

class Giving {

    @Composable
    fun GivingScreen(navController: NavHostController, modifier: Modifier) {

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = modifier.fillMaxSize().scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

//                Utils.TopBanner()
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
//                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = stringResource(id = R.string.giving_quote),
                    fontStyle = FontStyle.Italic,
                    fontSize = TextUnit(value = 20f, type = TextUnitType.Sp),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.padding(horizontal = 5.dp)
                        .weight(1f) // Give the text some weight to push down other content
                        .wrapContentHeight(align = Alignment.CenterVertically) // Center text vertically within its weighted space
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
                ) {
                    val givelifyUrl = "https://www.givelify.com/donate/life-renewed-harvest-ministries-gary-in-2j7wy5MzMzNTg=/donation/amount"
                    NavToWebpageButton(url = givelifyUrl, buttonText = R.string.givelify)

                    val cashAppUrl = "https://cash.app/LifeReNewedHarvest"
                    NavToWebpageButton(url = cashAppUrl, buttonText = R.string.cashApp)
                }
            }
        }


    }

    @Composable
    fun NavToWebpageButton(url: String, buttonText: Int) {
//        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
//            data = url.toUri()
//        }
        val context = LocalContext.current
        val intent = remember(url) { // Recreate intent if URL changes
            Intent(Intent.ACTION_VIEW, url.toUri())
            // No need to set data separately if provided in constructor for ACTION_VIEW
        }
        Button(
            onClick = {

//                if(intent.resolveActivity(context.packageManager) != null){
//                    context.startActivity(intent)
//                } else {
//                    //throw Exception("No activity found to handle intent")
//                    Toast.makeText(context, "No activity found to handle intent", Toast.LENGTH_LONG).show()
//                }
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
        ) {
            Text(
                text = stringResource(buttonText),
                color = Color.White
            )
        }
    }

    @Composable
    @Preview(showBackground = true)
    fun GivingScreenPreview() {
        GivingScreen(
            navController = NavHostController(context = LocalContext.current),
            modifier = Modifier
        )
    }

//    @Composable
//    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//    fun GivingScreenDarkPreview() {
//        GivingScreen(
//            navController = NavHostController(context = LocalContext.current),
//            modifier = Modifier
//        )
//    }
}