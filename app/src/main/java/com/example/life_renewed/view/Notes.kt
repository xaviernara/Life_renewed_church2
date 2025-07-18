package com.example.life_renewed.view

import android.content.res.Configuration
import android.graphics.fonts.FontStyle
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.life_renewed.R
import com.example.life_renewed.model.NotesObject
import com.example.life_renewed.navigation.NavScreens
import com.example.life_renewed.utils.Utils
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
            verticalArrangement = Arrangement.Top,
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

                    Image(
                        painter = painterResource(R.drawable.ic_insert_note),
                        contentDescription = "Empty Notes"
                    )
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
                }

                else -> {
                    LazyColumn {
                        item {
                            Utils.TopBanner()
                        }
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

    /*
        Suggestions:
       

        This is a great starting point for the notes section! Here are some UI improvement ideas for your Jetpack Compose app, keeping in mind animations, light/dark modes, themes, and various composables, all tailored for the "Life ReNewed Harvest Church" context:

        Overall UI Philosophy
        Clarity & Readability: Ensure notes are easy to read and differentiate.

        Intuitive Interaction: Make actions (edit, delete) clear and responsive.

        Brand Consistency: Align with the church's aesthetic (if one exists, otherwise establish a calming, inviting feel).

        Accessibility: Consider users with varying needs (e.g., sufficient contrast).

        Specific UI Improvement Ideas
        1. Card Design & Spacing
        Elevation & Shadows: Add subtle elevation to your Card Composables. This creates depth and makes them "pop" off the background. Use CardDefaults.cardElevation() for a consistent look.

        Rounded Corners: Increase the shape parameter of your Card to have more rounded corners for a softer, more modern feel.

        Consistent Padding: Ensure consistent vertical and horizontal padding around the content within each card, and also between the cards themselves. Spacer Composables can help with vertical separation.

        Clickable Card (for viewing detail): Make the entire card clickable to navigate to a detailed view of the note. This can be indicated by a ripple effect on click.

        2. Typography & Content Hierarchy
        Font Choice: While your current font is legible, consider a slightly softer or more spiritual-feeling font for the body text if it fits the church's brand. Keep "Series Title" clear and concise.

        Date/Time Formatting: Display the date in a more user-friendly format (e.g., "July 6, 2025" or "Today, 4:49 PM" if recent). You could also add the time if notes are created frequently.

        Ellipsis for Long Titles/Notes: For long "Series Title" or note content, use maxLines and TextOverflow.Ellipsis to truncate text neatly, inviting the user to tap for more details.

        3. Action Buttons (Edit/Delete)

                Icon Buttons: Instead of large text buttons, consider using IconButton Composables with descriptive icons (e.g., a pencil icon for "Edit," a trash can icon for "Delete"). This saves space and is more universally understood.

        Placement:

        Trailing Icon: For simple notes, you could place a small "Edit" icon within the card's trailing end.

        Collapsed Actions: Initially hide the "Edit" and "Delete" buttons and reveal them with an animation when the user long-presses or swipes the card.

        Bottom Sheet/Dialog: When the user clicks on a note, show a bottom sheet or a dialog with options like "View," "Edit," "Delete."

        Coloring:

        Edit: A neutral or primary color (e.g., your app's theme color, or even a soft blue) rather than bright green. Green can sometimes imply "success" or "add."

        Delete: Keep red for "Delete" as it universally signifies a destructive action, but perhaps a softer shade or an outlined button to be less jarring.

        Outlined Buttons: Consider OutlinedButton for less visual weight if you want them visible, or just TextButton if they are part of a dialog.


        4. Animations
            Item Entrance: When new notes are added or the screen loads, animate them subtly.

            Fade In: AnimatedVisibility or Crossfade for cards to fade in.

            Slide In: Make cards slide in from the bottom or side with a slight delay for each.

            Deletion Animation: When a note is deleted, animate its disappearance.

            Shrink & Fade Out: The card could shrink and fade away.

            Slide Out: The card could slide out to the side.

            Button Press Feedback: Ensure ripple effects are visible when buttons are pressed.
     */

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
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .animateContentSize()
                .clickable(
                    onClick = {
                        isExpanded = !isExpanded
                    }
                )
                .shadow(
                    elevation = 20.dp,
                    spotColor = Color.Magenta, // Set the desired shadow color here
                    ambientColor = Color.Red // Optional: Set ambient color for a different effect
                ),
            colors = CardDefaults.cardColors(Color.Unspecified),
            elevation = CardDefaults.cardElevation(20.dp),
            shape = RoundedCornerShape(
                topStart = 2.dp,
                topEnd = 2.dp,
                bottomEnd = 2.dp,
                bottomStart = 2.dp
            )
//            shape = CardDefaults.elevatedShape
        ) {
            Text(
                text = sermonTitle ?: "Sermon Title",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 10.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
//                textAlign = TextAlign.Center
            )
            Text(
                text = seriesTitle ?: "Series Title",
                modifier = Modifier.padding(horizontal = 10.dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
//                textAlign = TextAlign.Center
            )

            if (isExpanded) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Green),
                        onClick = {
                            navController.navigate(NavScreens.NoteDetail.route)
                        }
                    ) {
                        if (notesObject.description?.isEmpty() == true) {
                            Text(text = stringResource(R.string.add_notes))
                        } else {
                            Text(text = stringResource(R.string.edit_note))
                        }
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        onClick = {
                            viewModel.deleteNotes(notesObject)
                        }
                    ) {
                        Text(text = stringResource(R.string.delete_note))
                    }
                }
            }
        }
    }


    private fun getCardSize(isExpanded: Boolean): Size {
        val defaultCardWidth = 350f
        val defaultCardHeight = 100f
        val defaultExpandedHeight = 150f

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