package com.example.life_renewed.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.life_renewed.navigation.NavScreens

class About {

    @Composable
    fun AboutScreen(navController: NavHostController) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val titleList = listOf("Services", "Our Vision", "Our Mission", "Church Concrete")
                val itemsPerRow = 2

                LazyVerticalGrid(
                    columns = GridCells.Fixed(itemsPerRow),// Defines a grid with a fixed number of columns
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 8.dp,
                            end = 8.dp
                        ), // Overall padding for the grid container
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Space between columns
                    verticalArrangement = Arrangement.spacedBy(8.dp), // Space between rows
                    contentPadding = PaddingValues(vertical = 8.dp), // Padding for the content within the grid (top and bottom)
                ) {
                    items(titleList.size) { index ->
                        val title = titleList[index]
                        val route = when (index) {
                            0 -> NavScreens.Services.route
                            1 -> NavScreens.Vision.route
                            2 -> NavScreens.Mission.route
                            else -> NavScreens.ChurchConcrete.route
                        }
                        BulletinCardItem(
                            title = title,
                            navController = navController,
                            route = route
                        )
                    }

                }
            }
        }
    }

    @Composable
    fun BulletinCardItem(title: String, route: String, navController: NavHostController) {
        Card(
            colors = CardDefaults.cardColors(Color.Black),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = CardDefaults.elevatedShape,
            modifier = Modifier.size(width = 75.dp, height = 100.dp)
        ) {
            IconButton(
                onClick = { navController.navigate(route) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize()
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }

        }
    }

    @Composable
    @Preview
    fun AboutScreenPreview() {
        AboutScreen(navController = NavHostController(LocalContext.current))
    }
}