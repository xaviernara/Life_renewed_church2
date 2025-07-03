package com.example.life_renewed.view

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.life_renewed.R
import com.example.life_renewed.model.NotesObject
import com.example.life_renewed.navigation.NavScreens
import com.example.life_renewed.viewmodel.LifeRenewViewModel

class Notes {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun NotesScreen(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        viewModel: LifeRenewViewModel? = null,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ) {
            val isLoading = viewModel?.isLoading?.collectAsState()
            val error = viewModel?.error?.collectAsState()
            val notesList = viewModel?.allNotes?.collectAsState()

            when {
                isLoading?.value == true -> {
                    CircularProgressIndicator(
                        color = Color.Green
                    )
                }

                error?.value?.isNotEmpty() == true -> {

                    Text(
                        text = error.value,
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                }

                notesList?.value?.isEmpty() == true -> {

                    Image(painter = painterResource(R.drawable.ic_insert_note), contentDescription = "Empty Notes")
                    Text(
                        text = stringResource(R.string.add_notes),
                        color = Color.Blue,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            navController.navigate(NavScreens.NoteDetail.route)
                        }
                    )
//                    Button(
//                        colors = ButtonDefaults.buttonColors(Color.Green),
//                        onClick = {
//                            navController.navigate(NavScreens.NoteDetail.route)
//                        }
//                    ){
//                        Text(text = "Add Note")
//                    }
                }

                else -> {
                    LazyColumn {
//                        item {
//                            Utils.TopBanner()
//                        }
                        items(count = notesList?.value?.size ?: 0) { index ->

                            NotesItem(
                                notesObject = notesList?.value?.get(index) ?: NotesObject(),
                                navController = navController,
                                viewModel = viewModel!!
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun NotesItem(
        notesObject: NotesObject,
        navController: NavHostController,
        viewModel: LifeRenewViewModel
    ) {

        val sermonTitle by remember { mutableStateOf(notesObject.sermonTitle + " " + notesObject.date) }
        val seriesTitle by remember { mutableStateOf(notesObject.seriesTitle) }
        var isExpanded by remember { mutableStateOf(false) }
        val size: Size by animateSizeAsState(
            targetValue = getCardSize(isExpanded),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ) //adds bounce to the animation
        )

        Card(
            modifier = Modifier
                .size(size.width.dp, size.height.dp)
                .animateContentSize()
                .clickable(
                    onClick = {
                        isExpanded = !isExpanded
                    }
                ),
            colors = CardDefaults.cardColors(Color.Cyan)
        ) {
            Text(text = sermonTitle)
            Text(text = seriesTitle ?: "")

            if (isExpanded) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Green),
                        onClick = {
                            navController.navigate(NavScreens.NoteDetail.route)
                        }
                    ) {
                        if (notesObject.description?.isEmpty() == true) {
                            Text(text = "Add Note")
                        } else {
                            Text(text = "Edit Note")
                        }
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        onClick = {
                            viewModel.deleteNotes(notesObject)
                        }
                    ) {
                        Text(text = "Delete Note")
                    }
                }
            }
        }
    }


    private fun getCardSize(isExpanded: Boolean): Size {
        val defaultCardWidth = 350f
        val defaultCardHeight = 150f
        val defaultExpandedHeight = 300f

        return if (isExpanded)
            Size(
                width = defaultCardWidth,
                height = defaultExpandedHeight
            )
        else
            Size(
                width = defaultCardWidth,
                height = defaultCardHeight
            )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    @Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
    fun NotesScreenPreview() {
        NotesScreen(
            navController = NavHostController(LocalContext.current)
        )
    }
}