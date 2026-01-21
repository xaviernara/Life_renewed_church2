package com.example.life_renewed.view

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.life_renewed.R
import com.example.life_renewed.model.NotesObject
import com.example.life_renewed.utils.Utils
import com.example.life_renewed.viewmodel.LifeRenewViewModel

class NotesDetails {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun NoteDetailScreen(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        viewModel: LifeRenewViewModel? = null
    ) {
        //val noteTitle = navController.currentBackStackEntry?.arguments?.getString("noteTitle")
//        var description = navController.currentBackStackEntry?.arguments?.getString("description")
        var description by remember { mutableStateOf("") }
        var title  by remember { mutableStateOf("") }
        var date by remember { mutableStateOf(Utils.getCurrentDate()) }
        val context = LocalContext.current
        val timeStamp by remember { mutableStateOf(Utils.getCurrentTime()) }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            TextField(
                label = { Text(date.toString()) },
                value = title,
                onValueChange = { text->
                    title = text
                },
                modifier = Modifier.padding(vertical = 8.dp),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomEnd = 12.dp,
                    bottomStart = 12.dp
                ),
                colors = colors(
//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            TextField(
                label = { Text(stringResource(R.string.description)) },
                value = description,
                onValueChange = { text ->
                    description = text
                },
                modifier = Modifier
                    .weight(.5f)
                    .padding(vertical = 12.dp),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomEnd = 12.dp,
                    bottomStart = 12.dp
                ),
                colors = colors(
//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Button(
                onClick = {
                    val notesObject = NotesObject(
                        sermonTitle = title,
                        description = description,
                        date = date.toString(),
                        timeStamp = timeStamp.toString()
                    )
                    if(description.isEmpty()){
                        Toast.makeText(context, context.getString(R.string.note_empty), Toast.LENGTH_SHORT).show()
                    }else{
                        viewModel?.insertNotes(notesObject)
                        Toast.makeText(context, context.getString(R.string.note_saved), Toast.LENGTH_SHORT).show()
                    }
//                    navController.navigateUp()

                },
                colors = ButtonDefaults.buttonColors(Color.Green),
                modifier = Modifier.padding(vertical = 16.dp)
            ){
                Text(text = stringResource(R.string.save_note))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun NoteDetailScreenPreview() {
        NoteDetailScreen(
            navController = NavHostController(LocalContext.current)
        )

    }
}