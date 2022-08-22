package com.example.learn.nabtati.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learn.nabtati.presentation.pages.home.Home
import com.example.learn.nabtati.presentation.pages.home.PlantDetails
import com.example.learn.nabtati.presentation.pages.home.PlantsListViewModel
import com.example.learn.nabtati.presentation.ui.theme.DarkGreen

@Composable
fun Navigation(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    context: Context
) {
    val viewModel: PlantsListViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "home"){

        composable(route = "home"){
            val homeNavController = rememberNavController()


            NavHost(navController = homeNavController, startDestination = "home"){
                composable(route = "home"){
                    Home(
                        homeNavController,
                        context = context,
                        viewModel = viewModel
                    )
                }
                composable(route = "plant_details"){
                    PlantDetails(
                        homeNavController,
                        bottomBarState

                    )
                }
            }
        }
        composable(route = "garden"){
            Text(text = "garden")
        }

        composable(route = "cart"){
            Text(text = "cart")
        }

        composable(route = "profile"){
            Text(text = "profile")
        }

    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
    state: MutableState<Boolean> = mutableStateOf(true),

    ) {

    // if show bottom nav bar state is true we show it
    if (state.value){

        val backStackEntry = navController.currentBackStackEntryAsState()

        BottomNavigation(
            modifier = modifier,
            backgroundColor = Color.White,
            elevation = 5.dp,
        ) {
            items.forEach {item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected  ,
                    onClick = { onItemClick(item) },
                    icon = {
                        Column(
                            horizontalAlignment = CenterHorizontally
                        ) {
                            if (selected){
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = "",
                                    tint = DarkGreen
                                )
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    color = DarkGreen,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }else{
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = "",
                                )
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                )
            }
        }
    }

}