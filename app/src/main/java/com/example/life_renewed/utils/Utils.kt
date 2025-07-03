package com.example.life_renewed.utils

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.life_renewed.R
import com.example.life_renewed.navigation.NavScreens
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.text.format
import java.text.SimpleDateFormat

object Utils {


    @Composable
    fun TopBanner(drawable: Int = R.drawable.ic_launcher_background) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = drawable),
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
                // Or .FillBounds, .Fit, etc.
                // ContentScale.Crop will fill bounds and crop if necessary
                // ContentScale.FillBounds will stretch
            )
        }
    }


    @Composable
    fun getCurrentRoute(navController: NavHostController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }

//    /*
//        Returns the current date in the format "MM/dd/yyyy"
//     */
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getCurrentDate(): String {
//        val currentDate: LocalDate = LocalDate.now()
//        val year: Int = currentDate.year
//        val monthValue: Int = currentDate.monthValue // Month as a number (1-12)
//        val monthName: String = currentDate.month.toString() // Month as text (e.g., "JANUARY")
//        val dayOfMonth: Int = currentDate.dayOfMonth
//        val dayOfWeek: String = currentDate.dayOfWeek.toString() // Day of week as text (e.g., "MONDAY")
//
//        // Example 1: Custom pattern
//        val formatterCustom: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
//        val formattedDateCustom: String = currentDate.format(formatterCustom) // e.g., "12/25/2023"
//
//        return formattedDateCustom
//    }

//    fun getCurrentDate(): String {
//        // Get Calendar instance
//        val calendar: Calendar = Calendar.getInstance()
//
//        // Get individual components
//        val yearCal: Int = calendar.get(Calendar.YEAR)
//        val monthCal: Int = calendar.get(Calendar.MONTH) + 1 // Month is 0-indexed (0=January, 11=December)
//        val dayCal: Int = calendar.get(Calendar.DAY_OF_MONTH)
//
//
//        // Format the date
//        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//        val formattedDateCal: String = sdf.format(calendar.time) // e.g., "25/12/2023
//    }


    /*
        Returns the current date in the format "MM/dd/yyyy"
        ex: 12/25/2023
        @return String?
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): String? {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        return currentDate.format(formatter)
    }


}