package com.example.life_renewed

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.life_renewed.navigation.NavGraph
import com.example.life_renewed.navigation.NavScreens
import com.example.life_renewed.ui.theme.Life_renewedTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val scollState = rememberScrollState()
            val navController = rememberNavController()



            Life_renewedTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerContent(
                            scope = scope,
                            drawerState = drawerState,
                            navController = navController
                        )
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopNavBar(
                                scope = scope,
                                drawerState = drawerState,
                                navController = navController
                            )
                        },
                        bottomBar = { BottomNavBar(navController) },
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->

                        NavGraph().RootNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))

                    }

                }
            }
        }
    }

    @Composable
    fun ModalDrawerContent(
        scope: CoroutineScope,
        drawerState: DrawerState,
        navController: NavHostController
    ) {
        // Content of the navigation drawer
        ModalDrawerSheet {
            Text("Drawer Title", modifier = Modifier.padding(16.dp))
            HorizontalDivider()
            NavigationDrawerItem(
                label = { Text("About") },
                selected = false,
                onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(NavScreens.About.route)
                    // Navigate to Screen 1
                }
            )
            NavigationDrawerItem(
                label = { Text("Links") },
                selected = false,
                onClick = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate(NavScreens.Links.route)
                    }
                    // Navigate to Screen 1
                }
            )
            NavigationDrawerItem(
                label = { Text("Connect") },
                selected = false,
                onClick = {
                    scope.launch {
                        drawerState.close()
                        navController.navigate(NavScreens.ConnectForm.route)
                    }
                    // Navigate to Screen 1
                }
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopNavBar(
        scope: CoroutineScope,
        drawerState: DrawerState,
        navController: NavHostController
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Life ReNewed Harvest Church",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = TextUnit(value = 12f, type = TextUnitType.Sp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(Color.Black),
            navigationIcon = {
//                Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                IconButton(
                    onClick = {
                        Log.d("TopNavBar", "Navigation icon clicked")
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(Color.Black),
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                )
            }
        )
    }


    @Composable
    fun BottomNavBar(navController: NavHostController) {
        NavigationBar(
            containerColor = Color.Black
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu") },
                label = { Text("Bulletin") },
                selected = false,
                onClick = { navController.navigate(NavScreens.Bulletin.route) }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Filled.LocationOn, contentDescription = "Menu") },
                label = { Text("Location") },
                selected = false,
                onClick = { navController.navigate(NavScreens.Map.route) }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = stringResource(R.string.home)
                    )
                },
                label = { Text(stringResource(R.string.home)) },
                selected = false,
                onClick = {
                    navController.navigate(NavScreens.Home.route)
                }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu") },
                label = { Text("Give") },
                selected = false,
                onClick = { navController.navigate(NavScreens.Giving.route) }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu") },
                label = { Text("Announcements") },
                selected = false,
                onClick = { navController.navigate(NavScreens.Announcements.route) }
            )

        }

    }
}
