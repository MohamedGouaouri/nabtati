package com.example.learn.nabtati.presentation.pages.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.learn.R
import com.example.learn.nabtati.presentation.ui.theme.GreenWhite
import com.example.learn.nabtati.presentation.ui.theme.LightGreen4
import com.example.learn.nabtati.presentation.ui.theme.LightGrey
import com.example.learn.nabtati.presentation.ui.theme.OrangeYellow4
import com.example.learn.nabtati.services.notifications.NabtatiNotificationService
import com.example.learn.nabtati.sockets.SocketHandler
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Home(
    navController: NavController,
    viewModel: PlantsListViewModel,
    context: Context
) {

    val notificationService = NabtatiNotificationService(context)

    var tipCardDismissed by remember {
        mutableStateOf(false)
    }

    val state by viewModel.state

    var notificationCount by remember {
        mutableStateOf(1)
    }

    var swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    try {
        SocketHandler.establishConnection()
        SocketHandler.getSocket().on("notification"){ args ->
            val value = args[0] as Int
            CoroutineScope(Dispatchers.Main).launch {
                notificationCount = value
            }
        }
    }catch (_: Exception){
        SocketHandler.closeConnection()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = GreenWhite)
        .padding(horizontal = 20.dp)
        .padding(top = 10.dp)
    ){
        Column {
            // Top bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SearchTopAppBar(
                    modifier = Modifier
                        .weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(10.dp)

                ) {
                    if (notificationCount > 0){
                        BadgedBox(
                            badge =  {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .background(Color.Red),
                                    contentAlignment = Alignment.Center
                                ){
                                    Text(
                                        text = notificationCount.toString(),
                                        color = Color.White
                                    )
                                }
                            },
                            modifier = Modifier
                                .clickable {
                                    notificationCount++
                                    Log.d("NOTIFICATION", "increment")
                                }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(5.dp)
                                    .clickable {
//                                        notificationService.showNotification("Heey new plant is here")
                                    }
                            )
                        }
                    }else{
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "",
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

           if (state.displayTip){
               if (!tipCardDismissed && state.tip != null){
                   // Today's tip card
                   TipCard(
                       tip = state.tip!!.tip,
                       imageResource = painterResource(id = R.drawable.leaf),
                       dismiss = {
                           tipCardDismissed = true
                           viewModel.onEvent(PlantsListEvents.SwipeTipCard)
                       }
                   )
               }

           }

            Chips(
                items = mutableListOf("All", "Popular", "New Arrivals", "Best Seller")
            )

            Spacer(modifier = Modifier.height(20.dp))


            // Swipe refresh
            
            SwipeRefresh(state = swipeRefreshState, onRefresh = {
                viewModel.onEvent(PlantsListEvents.Refresh)
            }) {

                // List of plants
                if (state.error.isBlank()){
                    LazyColumn{
                        items(state.plants){plant ->
                            PlantCard(
                                imageResource = painterResource(id = R.drawable.plant1),
                                plant = plant,
                                viewModel = viewModel,
                                onClick = {
                                    navController.navigate("plant_details")
                                }
                            )
                        }

                    }
                }

                // Error and loading state area
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    if(state.error.isNotBlank()) {
                        Text(
                            text = state.error,
                            color = MaterialTheme.colors.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .align(Alignment.Center)
                        )
                    }
                    if(state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center),
                            color = LightGreen4
                        )
                    }
                }
                
            }

        }
    }
}

@Composable
fun SearchTopAppBar(
    modifier: Modifier = Modifier,
) {

    var value by remember {
        mutableStateOf("")
    }

    Box(
        modifier = modifier
            .height(45.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
            )
            BasicTextField(
                value = value,
                onValueChange = {
                    value = it
                },
            )
        }

    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TipCard(
    tip: String,
    imageResource: Painter,
    dismiss: () -> Unit

) {
    val dismissState = rememberDismissState(
        initialValue = DismissValue.Default,
        confirmStateChange = {
            dismiss()
            true
        }
    )


    SwipeToDismiss(state = dismissState, background = {}) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(OrangeYellow4)
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                ) {
                    Image(
                        painter = imageResource,
                        contentDescription = "",
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Today\'s tip",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = tip ?: "",
                        color = LightGrey,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}