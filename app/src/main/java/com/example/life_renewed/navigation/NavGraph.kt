package com.example.life_renewed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.life_renewed.view.About
import com.example.life_renewed.view.Home
import kotlinx.serialization.Serializable

class NavGraph {

    @Composable
    fun RootNavGraph(navController: NavHostController, modifier: Modifier){

        NavHost(navController = navController, startDestination = NavScreens.Home.route){
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

        }
    }

}

@Serializable
data class About2(var id: Int, var name: String)



