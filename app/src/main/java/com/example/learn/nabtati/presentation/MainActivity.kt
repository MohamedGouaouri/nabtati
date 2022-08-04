package com.example.learn.nabtati.presentation



import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.learn.nabtati.presentation.navigation.BottomNavItem
import com.example.learn.nabtati.presentation.navigation.BottomNavigationBar
import com.example.learn.nabtati.presentation.navigation.Navigation
import com.example.learn.nabtati.services.notifications.NabtatiNotificationService
import com.example.learn.nabtati.sockets.SocketHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            finishActivity(0)
            val navController = rememberNavController()
            var bottomBarState = remember {
                mutableStateOf(true)
            }

            SocketHandler.setSocket()

            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem(
                                name = "Home",
                                route = "home",
                                icon = Icons.Default.Home
                            ),
                            BottomNavItem(
                                name = "Garden",
                                route = "garden",
                                icon = Icons.Default.Notifications
                            ),
                            BottomNavItem(
                                name = "Cart",
                                route = "cart",
                                icon = Icons.Default.ShoppingCart
                            ),
                            BottomNavItem(
                                name = "Profile",
                                route = "profile",
                                icon = Icons.Default.Settings
                            )
                        ),
                        navController =
                        navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        },
                        state = bottomBarState
                    )
                }
            ){
                Navigation(
                    navController = navController,
                    bottomBarState = bottomBarState,
                    context = applicationContext
                )
            }
        }
    }


}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}