package com.example.life_renewed.view


import android.content.Context
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.life_renewed.R
import com.example.life_renewed.model.BulletinItem
import kotlinx.coroutines.launch

class About {

    private fun getTitleAndDescription(index: Int, context: Context) :Pair<String, Any>{
        val titleArray : Array<String> = context.resources.getStringArray(R.array.about_screen_card_titles)
        return when(index){
            0 -> Pair(titleArray[index], context.resources.getStringArray(R.array.church_services))
            1 -> Pair(titleArray[index], context.resources.getString(R.string.church_vision))
            2 -> Pair(titleArray[index], context.resources.getString(R.string.church_mission))
            else -> Pair(titleArray[index], context.resources.getStringArray(R.array.church_concretes))
        }

    }

    private fun getBulletinItems(index: Int, context: Context) : BulletinItem{
        val titleArray : Array<String> = context.resources.getStringArray(R.array.about_screen_card_titles)
        return when(index){
            0 -> BulletinItem(
                id = index,
                title = titleArray[index],
                longDescription = context.resources.getStringArray(R.array.church_services).toList()
            )
            1 -> BulletinItem(
                id = index,
                title = titleArray[index],
                shortDescription = context.resources.getString(R.string.church_vision),
                )
            2 -> BulletinItem(
                id = index,
                title = titleArray[index],
                shortDescription = context.resources.getString(R.string.church_mission),
            )
            else -> BulletinItem(
                id = index,
                title = titleArray[index],
                longDescription = context.resources.getStringArray(R.array.church_concretes).toList()
            )
        }


    }



    @Composable
    fun AboutScreen(navController: NavHostController) {
        val context = LocalContext.current
//        val titleArray : Array<String> = context.resources.getStringArray(R.array.about_screen_card_titles)
//        val descriptionArray: Array<String> = when(index){
//            0 -> context.resources.getStringArray(R.array.church_services)
//            else -> context.resources.getStringArray(R.array.church_concretes)
//        }
//        val description = when (index) {
//            1 -> stringResource(R.string.church_vision)
//            else -> stringResource(R.string.church_mission)
//        }
//        val bulletinItems = List(4) { index ->
//            BulletinItem(
//                id = index,
////                title = titleArray,
////                shortDescription = when (index) {
////                    1 -> stringResource(R.string.church_vision)
////                    else -> stringResource(R.string.church_mission)
////                },
////                longDescription = when(index){
////                    0 -> context.resources.getStringArray(R.array.church_services)
////                    3 -> context.resources.getStringArray(R.array.church_concretes)
////                    else -> {}
////                }
//                title = getTitleAndDescription(index,context).first,
//                shortDescription = getTitleAndDescription(index,context).second.toString(),
//                longDescription = getTitleAndDescription(index,context).second as Array<String>
//            )
//        }

        val bulletinItems = List(4) { index ->
            getBulletinItems(index,context)
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                val itemsPerRow = 2

                AboutImage()

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
//                    items(titleArray.size) { index ->
//                        val title = titleArray[index]
//
//                        val descriptionArray: Array<String> = when(index){
//                            0 -> context.resources.getStringArray(R.array.church_services)
//                            else -> context.resources.getStringArray(R.array.church_concretes)
//                        }
//                        val description = when (index) {
//                            1 -> stringResource(R.string.church_vision)
//                            else -> stringResource(R.string.church_mission)
//                        }
//                        BulletinCardItem(
//                            title = title,
//                            description = description,
//                            descriptionArray = descriptionArray
////                            navController = navController,
////                            route = route
//                        )
//                    }
                    items(bulletinItems.size) { index ->
                        BulletinCardItem(
                            title = bulletinItems[index].title,
                            description = bulletinItems[index].shortDescription ?: "",
                            descriptionArray = bulletinItems[index].longDescription ?: emptyList()
//                            navController = navController,
//                            route = route
                        )
                    }

                }
            }
        }
    }

    @Composable
    fun AboutImage() {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                contentDescription = null
            )
        }
    }

    @Composable
    fun BulletinCardItem(
        title: String,
        description: String?,
        descriptionArray: List<String>?
    ) {
        var showDescription by remember { mutableStateOf(false) }
        val size: Size by animateSizeAsState(
            targetValue = if (showDescription) Size(width = 100f, height = 200f) else Size(width = 75f,height= 100f),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ) //adds bounce to the animation
        )



        Card(
            colors = CardDefaults.cardColors(Color.Black),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = CardDefaults.elevatedShape,
            modifier = Modifier.size(size.width.dp, size.height.dp)
        ) {
            IconButton(
                onClick = {
//                    navController.navigate(route)
                    showDescription = !showDescription
                          },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize()
            ) {
                if(showDescription){
//                    if(descriptionArray?.isNotEmpty() == true){
//                        descriptionArray.forEach{
//                            Text(
//                                text = it,
//                                color = Color.White,
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }else{
//                        Text(
//                            text = description ?: "",
//                            color = Color.White,
//                            textAlign = TextAlign.Center
//                        )
//                    }
                    DescriptionBottomsheet(
                        description = description,
                        descriptionArray = descriptionArray,
                        showDescription = showDescription
                    )

                }else{
                    Text(
                        text = title,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DescriptionBottomsheet(
        description: String?,
        descriptionArray: List<String>?,
        showDescription: Boolean = false
    ){
        val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
        val coroutineScope = rememberCoroutineScope()
//        var showBottomSheet by remember { mutableStateOf(showDescription) }

        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    bottomSheetState.hide()
//                    showDescription = false
                } },
            sheetState = bottomSheetState,
            modifier = Modifier.padding(bottom = 16.dp)
        ){

            if(descriptionArray?.isNotEmpty() == true){
                descriptionArray.forEach{
                    Text(
                        text = it,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }else{
                Text(
                    text = description ?: "",
                    color = Color.White,
                    textAlign = TextAlign.Center
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