package com.example.life_renewed.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.life_renewed.R

class Home {

    @Composable
    fun HomeScreen(navController: NavHostController, modifier: Modifier){
        val scollState = rememberScrollState()


        Column(
            modifier = modifier
//                .padding(innerPadding) // Apply padding from Scaffold
                .fillMaxSize()// Make the column fill the available space
                .verticalScroll(
                    state = scollState
                )
        ) {
            HomeImage()
            HomeBodyInfo()
        }

    }





    @Composable
    fun HomeImage(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(.5f),
            contentAlignment = Alignment.TopCenter
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Menu",
                modifier = modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            Button(
                onClick = { Log.d("HomeImage", "Button clicked") },
                shape = RoundedCornerShape(corner = CornerSize(20.dp)), // Applies a rounded corner shape to the button
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = modifier
                    .align(Alignment.BottomCenter) // Align the button to the bottom center of the Box
                    .fillMaxWidth(0.4f) // Make the button fill 40% of the Box width
                    .padding(bottom = 15.dp), // Add some padding from the bottom edge
            ) {
                Text(text = "Learn More")
            }
        }
    }


    @Composable
    fun HomeBodyInfo() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 24.dp)
        ) {

            Column {
                //            HorizontalDivider(
//                color = Color.Black,
//                thickness = 2.dp,
//                modifier = Modifier.fillMaxWidth()
//            )

                Text(
                    text = stringResource(R.string.warm_church_title),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(value = 24f, type = TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                )
//        Text(
//            text = getString(LocalContext.current,R.string.warm_church_body),
//            color = Color.Black,
//            textAlign = TextAlign.Center,
//            fontSize = TextUnit(value = 12f, type = TextUnitType.Sp),
//        )
                Text(
                    text = " We are a warm, Christ-centered community that embraces everyone with open arms. Whether you\\'re just beginning to explore your faith, deepening your spiritual walk, or searching for a church home, you\\'ll find a loving space here with us. Join us for engaging worship services designed to uplift your spirit, along with practical teachings that inspire your journey. Connect with others through our vibrant small groups, outreach programs, and service opportunities. No matter who you are or what stage of life you're in, there's a special place for you in our family. We invite you to come just as you are and experience the hope, healing, and purpose that arise from walking with Jesus. We can't wait to meet you!",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(value = 12f, type = TextUnitType.Sp),
                    fontWeight = FontWeight.Normal
                )

                Text(
                    text = stringResource(R.string.harvest_highlights),
                    color = Color.Black,
                    fontSize = TextUnit(value = 24f, type = TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    modifier =Modifier.align(Alignment.CenterHorizontally)
                )


                Text(
                    text = stringResource(R.string.harvest_highlights_body),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(value = 16f, type = TextUnitType.Sp),
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp, top = 10.dp)
                )

                Button(
                    onClick = { Log.d("HomeImage", "Button clicked") },
                    shape = RoundedCornerShape(corner = CornerSize(20.dp)), // Applies a rounded corner shape to the button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Align the button to the bottom center of the Box
//                        .fillMaxWidth(0.4f) // Make the button fill 40% of the Box width
                        .padding(bottom = 15.dp), // Add some padding from the bottom edge
                ){
                    Text(text= stringResource(R.string.church_bulletin))
                } // Applies a rounded corner shape to the button)


            }

        }

    }



    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview(){
        HomeScreen(rememberNavController(),Modifier)
    }
}