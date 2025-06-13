package com.example.life_renewed.view


import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.life_renewed.R
import com.example.life_renewed.model.ChurchInfoItem
import kotlinx.coroutines.launch
import kotlin.text.contains
import kotlin.text.indexOf
import kotlin.text.substring

class About {

    /**
     * Builds a [ChurchInfoItem] based on the provided index.
     *
     * This function constructs a [ChurchInfoItem] with details like title, short description,
     * or a list of long descriptions, sourcing the content from string resources based on the given `index`.
     *
     * @param index The index to determine which church information item to build.
     *              - 0: Builds item for church services.
     *              - 1: Builds item for church vision.
     *              - 2: Builds item for church mission.
     *              - Else: Builds item for church concretes.
     * @param context The [Context] used to access resources.
     * @return A [ChurchInfoItem] containing the information corresponding to the `index`.
     */
    private fun getChurchInfoItemsByIndex(index: Int, context: Context): ChurchInfoItem {
        val titleArray: Array<String> =
            context.resources.getStringArray(R.array.about_screen_card_titles)
        return when (index) {
            0 -> ChurchInfoItem(
                id = index,
                title = titleArray[index],
                longDescription = context.resources.getStringArray(R.array.church_services).toList()
            )

            1 -> ChurchInfoItem(
                id = index,
                title = titleArray[index],
                shortDescription = context.resources.getString(R.string.church_vision),
            )

            2 -> ChurchInfoItem(
                id = index,
                title = titleArray[index],
                shortDescription = context.resources.getString(R.string.church_mission),
            )

            else -> ChurchInfoItem(
                id = index,
                title = titleArray[index],
                longDescription = context.resources.getStringArray(R.array.church_concretes)
                    .toList()
            )
        }
    }


//    @Composable
//    fun AboutScreen(navController: NavHostController) {
//        val context = LocalContext.current
////        val titleArray : Array<String> = context.resources.getStringArray(R.array.about_screen_card_titles)
////        val descriptionArray: Array<String> = when(index){
////            0 -> context.resources.getStringArray(R.array.church_services)
////            else -> context.resources.getStringArray(R.array.church_concretes)
////        }
////        val description = when (index) {
////            1 -> stringResource(R.string.church_vision)
////            else -> stringResource(R.string.church_mission)
////        }
////        val bulletinItems = List(4) { index ->
////            BulletinItem(
////                id = index,
//////                title = titleArray,
//////                shortDescription = when (index) {
//////                    1 -> stringResource(R.string.church_vision)
//////                    else -> stringResource(R.string.church_mission)
//////                },
//////                longDescription = when(index){
//////                    0 -> context.resources.getStringArray(R.array.church_services)
//////                    3 -> context.resources.getStringArray(R.array.church_concretes)
//////                    else -> {}
//////                }
////                title = getTitleAndDescription(index,context).first,
////                shortDescription = getTitleAndDescription(index,context).second.toString(),
////                longDescription = getTitleAndDescription(index,context).second as Array<String>
////            )
////        }
//
//        val bulletinItems = List(4) { index ->
//            getBulletinItems(index,context)
//        }
//        var expandedItem by remember { mutableStateOf<Int>(0) }
//
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//
//
//                val itemsPerRow = 2
//
//                AboutImage()
//
//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(itemsPerRow),// Defines a grid with a fixed number of columns
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(
//                            start = 8.dp,
//                            end = 8.dp
//                        ), // Overall padding for the grid container
//                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Space between columns
//                    verticalArrangement = Arrangement.spacedBy(8.dp), // Space between rows
//                    contentPadding = PaddingValues(vertical = 8.dp), // Padding for the content within the grid (top and bottom)
//                ) {
////                    items(titleArray.size) { index ->
////                        val title = titleArray[index]
////
////                        val descriptionArray: Array<String> = when(index){
////                            0 -> context.resources.getStringArray(R.array.church_services)
////                            else -> context.resources.getStringArray(R.array.church_concretes)
////                        }
////                        val description = when (index) {
////                            1 -> stringResource(R.string.church_vision)
////                            else -> stringResource(R.string.church_mission)
////                        }
////                        BulletinCardItem(
////                            title = title,
////                            description = description,
////                            descriptionArray = descriptionArray
//////                            navController = navController,
//////                            route = route
////                        )
////                    }
//                    items(bulletinItems.size) { index ->
//                        BulletinCardItem(
//                            title = bulletinItems[index].title,
//                            description = bulletinItems[index].shortDescription ?: "",
//                            descriptionArray = bulletinItems[index].longDescription ?: emptyList()
////                            navController = navController,
////                            route = route
//                        )
//                    }
//
//                }
//            }
//        }
//    }

//    @Composable
//    fun AboutScreen(navController: NavHostController) {
//        val context = LocalContext.current
////        val titleArray : Array<String> = context.resources.getStringArray(R.array.about_screen_card_titles)
////        val descriptionArray: Array<String> = when(index){
////            0 -> context.resources.getStringArray(R.array.church_services)
////            else -> context.resources.getStringArray(R.array.church_concretes)
////        }
////        val description = when (index) {
////            1 -> stringResource(R.string.church_vision)
////            else -> stringResource(R.string.church_mission)
////        }
////        val bulletinItems = List(4) { index ->
////            BulletinItem(
////                id = index,
//////                title = titleArray,
//////                shortDescription = when (index) {
//////                    1 -> stringResource(R.string.church_vision)
//////                    else -> stringResource(R.string.church_mission)
//////                },
//////                longDescription = when(index){
//////                    0 -> context.resources.getStringArray(R.array.church_services)
//////                    3 -> context.resources.getStringArray(R.array.church_concretes)
//////                    else -> {}
//////                }
////                title = getTitleAndDescription(index,context).first,
////                shortDescription = getTitleAndDescription(index,context).second.toString(),
////                longDescription = getTitleAndDescription(index,context).second as Array<String>
////            )
////        }
//
//        val bulletinItems = List(4) { index ->
//            getBulletinItems(index,context)
//        }
//        var expandedItem by remember { mutableIntStateOf(0) }
////        val gridCells = GridCells.Adaptive(minSize = 150.dp)
//
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                val itemsPerRow = 2
//                AboutImage()
//                LazyVerticalGrid(
//                    columns = GridCells.Adaptive(150.dp),// Defines a grid with a fixed number of columns
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 8.dp,), // Overall padding for the grid container
//                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Space between columns
//                    verticalArrangement = Arrangement.spacedBy(8.dp), // Space between rows
//                    contentPadding = PaddingValues(vertical = 8.dp) // Padding for the content within the grid (top and bottom)
//                ) {
//                    items(
//                        items = bulletinItems,
//                        key = { bulletinItems[].id },
//                        span = { item ->
//                            val isCurrentItemExpanded = item == expandedItem
//                            if(isCurrentItemExpanded) GridItemSpan(currentLineSpan = maxLineSpan)
//                            else GridItemSpan(currentLineSpan = 1)
//                        }
//                    ) { index ->
//                        BulletinCardItem(
//                            title = bulletinItems[index].title,
//                            description = bulletinItems[index].shortDescription ?: "",
//                            descriptionArray = bulletinItems[index].longDescription ?: emptyList(),
//                            isExpanded = index == expandedItem,
//                            onItemClick = {
//                                expandedItem = if (bulletinItems[index].id == expandedItem) -1 else bulletinItems[index].id
//                            }
////                            navController = navController,
////                            route = route
//                        )
//                    }
//                }
//            }
//        }
//    }

    @Composable
    fun AboutScreen() {
        val context = LocalContext.current

        val bulletinItems = List(4) { index ->
            getChurchInfoItemsByIndex(index, context)
        }

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical
                )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp), // Overall padding for the grid container
                    verticalArrangement = Arrangement.spacedBy(12.dp), // Space between rows,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(vertical = 8.dp) // Padding for the content within the grid (top and bottom)
                ) {
                    item{
                        ChurchInfoBanner()
                    }
                    items(
                        bulletinItems.size
                    ) { index ->
                        ChurchInfoCardItem(
                            title = bulletinItems[index].title,
                            description = bulletinItems[index].shortDescription ?: "",
                            descriptionArray = bulletinItems[index].longDescription ?: emptyList(),
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ChurchInfoBanner() {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds // Or .FillBounds, .Fit, etc.
                // ContentScale.Crop will fill bounds and crop if necessary
                // ContentScale.FillBounds will stretch
            )
        }
    }

    /**
     * Calculates the size of a card based on whether the description is shown and the size of an array.
     *
     * @param showDescription A boolean indicating whether the description is visible.
     * @param arraySize The size of the array, which influences the card's height.
     * @return A [Size] object representing the width and height of the card.
     *
     * The card has a default width of 350f.
     * If the description is not shown or the array is empty, the card has a default height of 150f.
     * If the description is shown:
     *  - If the array size is 8, the card has a large height of 500f.
     *  - Otherwise, the card has a default expanded height of 200f.
     */
    private fun getCardSize(showDescription: Boolean, arraySize: Int): Size {
        val defaultCardWidth = 350f
        val defaultCardHeight = 150f
        val largeCardHeight = 500f
        val defaultExpandedHeight = 200f

        if (!showDescription || arraySize == 0) return Size(
            width = defaultCardWidth,
            height = defaultCardHeight
        )

        return when (arraySize) {
            8 -> Size(width = defaultCardWidth, height = largeCardHeight)
            else -> Size(width = defaultCardWidth, height = defaultExpandedHeight)
        }
    }



    /**
     * Formats the description text, making the phrase "We believe" bold if present.
     *
     * @param text The input string to be formatted.
     * @return An [AnnotatedString] with "We believe" in bold if found, otherwise the original text.
     */
    private fun getDescriptionText(text: String): AnnotatedString {
        val boldText = if (text.contains("We believe")) {
            buildAnnotatedString {
                val startIndex = text.indexOf("We believe")
                append(text.substring(0, startIndex))
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("We believe")
                }
                append(text.substring(startIndex + "We believe".length))
            }
        } else {
            // Fallback if "We believe" not in this specific item
            buildAnnotatedString { append(text) } // Or just textItem directly
        }
        return boldText
    }

    /**
     * A composable function that displays a card with a title and a description.
     * The card can be clicked to expand and show the full description.
     * The description can be a single string or a list of strings.
     * The card has a background image when it is not expanded.
     * The card animates its size when it is expanded or collapsed.
     *
     * @param title The title of the card.
     * @param description The short description of the card.
     * @param descriptionArray The long description of the card, as a list of strings.
     */
    @Composable
    fun ChurchInfoCardItem(
        title: String,
        description: String,
        descriptionArray: List<String>
    ) {
        var showDescription by remember { mutableStateOf(false) }
        val size: Size by animateSizeAsState(
            targetValue = getCardSize(showDescription, descriptionArray.size),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ) //adds bounce to the animation
        )

        Card(
            colors = CardDefaults.cardColors(Color.Black),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = CardDefaults.elevatedShape,
            modifier = Modifier
                .size(size.width.dp, size.height.dp)
                .animateContentSize()
                .clickable(
                    onClick = {
                        showDescription = !showDescription
                    }
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if(!showDescription){
                    // Background Image
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null, // Provide a meaningful description if decorative
                        modifier = Modifier.fillMaxSize(), // Image fills the Box (and thus the Card)
                        contentScale = ContentScale.Crop // Or .FillBounds, .Fit, etc.
                        // ContentScale.Crop will fill bounds and crop if necessary
                        // ContentScale.FillBounds will stretch
                    )
                }

                // Foreground Content - Centered on top of the image
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Padding for the text content
                    contentAlignment = Alignment.Center
                ) {
                    if (showDescription) {
                        if (descriptionArray.isNotEmpty()) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.scrollable(
                                    state = rememberScrollState(),
                                    orientation = Orientation.Vertical
                                )
                            ) {
                                descriptionArray.forEach { textItem ->
                                    Text(
                                        text = getDescriptionText(text = textItem),
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }
                        } else {
                            Text(
                                text = description,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        //About screen card title
                        Text(
                            text = title,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = TextUnit(value = 25f, type = TextUnitType.Sp),
                            fontFamily = FontFamily.Cursive
                        )
                    }
                }
            }
        }
    }


//    @Composable
//    fun BulletinCardItemToDisplayBottomSheet(
//        title: String,
//        description: String?,
//        descriptionArray: List<String>?
//    ) {
//        var showDescription by remember { mutableStateOf(false) }
//        val size: Size by animateSizeAsState(
//            targetValue = if (showDescription) Size(width = 100f, height = 200f) else Size(width = 75f,height= 100f),
//            animationSpec = spring(
//                dampingRatio = Spring.DampingRatioMediumBouncy,
//                stiffness = Spring.StiffnessLow
//            ) //adds bounce to the animation
//        )
//
//        Card(
//            colors = CardDefaults.cardColors(Color.Black),
//            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
//            shape = CardDefaults.elevatedShape,
//            modifier = Modifier.size(size.width.dp, size.height.dp)
//        ) {
//            IconButton(
//                onClick = {
////                    navController.navigate(route)
//                    showDescription = !showDescription
//                          },
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .fillMaxSize()
//            ) {
//                if(showDescription){
////                    if(descriptionArray?.isNotEmpty() == true){
////                        descriptionArray.forEach{
////                            Text(
////                                text = it,
////                                color = Color.White,
////                                textAlign = TextAlign.Center
////                            )
////                        }
////                    }else{
////                        Text(
////                            text = description ?: "",
////                            color = Color.White,
////                            textAlign = TextAlign.Center
////                        )
////                    }
//                    DescriptionBottomSheet(
//                        description = description,
//                        descriptionArray = descriptionArray,
//                        showDescription = showDescription
//                    )
//
//                }else{
//                    showDescription = false
//                    Text(
//                        text = title,
//                        color = Color.White,
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
//
//        }
//    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DescriptionBottomSheet(
        description: String?,
        descriptionArray: List<String>?,
        showDescription: Boolean = false
    ) {
        val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
        val coroutineScope = rememberCoroutineScope()
//        var showBottomSheet by remember { mutableStateOf(showDescription) }

        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    bottomSheetState.hide()
//                    showDescription = false
                }
            },
            sheetState = bottomSheetState,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {

            if (descriptionArray?.isNotEmpty() == true) {
                descriptionArray.forEach {
                    Text(
                        text = it,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
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
        AboutScreen()
    }
}