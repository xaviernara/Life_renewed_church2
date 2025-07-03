package com.example.life_renewed.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.life_renewed.view.About
import com.example.life_renewed.view.Announcements
import com.example.life_renewed.view.Bulletin
import com.example.life_renewed.view.ConnectForm
import com.example.life_renewed.view.Giving
import com.example.life_renewed.view.Home
import com.example.life_renewed.view.Location
import com.example.life_renewed.view.Notes
import com.example.life_renewed.view.NotesDetails
import com.example.life_renewed.view.Onboarding
import com.example.life_renewed.view.Splash
import com.example.life_renewed.viewmodel.LifeRenewViewModel
import kotlinx.serialization.Serializable

class NavGraph {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun RootNavGraph(navController: NavHostController, modifier: Modifier, viewModel: LifeRenewViewModel){

        NavHost(navController = navController, startDestination = NavScreens.Splash.route){
//            composable<Home>{
////                navController.navigate(NavScreens.Home.route)
////                navController.navigate(About2(id = 1, name = "Xavier"))
//                Home().HomeScreen(navController)
//            }

            composable(NavScreens.Home.route){
//                navController.navigate(NavScreens.Home.route)
//                navController.navigate(About2(id = 1, name = "Xavier"))
                Home().HomeScreen(navController,modifier)
            }

            /*
                Type safe navigation in compose
                https://youtu.be/AIC_OFQ1r3k?si=qQrkx9YYS_0iWeZJ


             */
//            composable<About2>{
//                val args = it.toRoute<About2>()
////                navController.navigate(NavScreens.About.route)
//                Column {
//                    Text(text = "id: " + args.id.toString() + "name" + args.name)
//                }
//            }
            composable(NavScreens.About.route){
                About().AboutScreen()
            }
            composable(NavScreens.Splash.route){
                Splash().SplashScreen(navController,modifier)
            }
            composable(NavScreens.Announcements.route){
                Announcements().AnnouncementsScreen(navController,modifier)
            }
            composable(NavScreens.ConnectForm.route){
                ConnectForm().ConnectFormScreen(navController,modifier)
            }
            composable(NavScreens.Map.route){
                Location().MapScreen(navController,modifier)
            }
            composable(NavScreens.Giving.route){
                Giving().GivingScreen(navController,modifier)
            }
            composable(NavScreens.Bulletin.route){
                Bulletin().BulletinScreen(navController,modifier)
            }

            composable(NavScreens.Onboarding.route) {
                Onboarding().OnboardingScreen(navController)
            }
            composable(NavScreens.Notes.route) {
                Notes().NotesScreen(navController = navController,viewModel = viewModel, modifier = modifier)
            }
            composable(NavScreens.NoteDetail.route) {
                NotesDetails().NoteDetailScreen(navController = navController,viewModel = viewModel, modifier = modifier)
            }
        }
    }

}

@Serializable
data class About2(var id: Int, var name: String)



