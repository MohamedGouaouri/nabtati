package com.example.learn.nabtati.presentation.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learn.R
import com.example.learn.nabtati.presentation.ui.theme.*


@Composable
fun PlantDetails(
//    plantDetails: PlantDetails
    navController: NavController,
    bottomBarState: MutableState<Boolean>

) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    when (backStackEntry.value?.destination?.route){
        "plant_details" -> {
            bottomBarState.value = false
        }
    }

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp


    val imageHeight = (screenHeight - 100.dp ) / 2.dp
    val descHeight = (screenHeight - 100.dp) / 2.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenWhite)
    ){
        Column(
        ) {
            // Top
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = "",
                )
            }

            // Plant big image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = com.example.learn.R.drawable.plant1),
                    contentDescription = "",
                    modifier = Modifier
                        .height(200.dp)
                )

            }

            // Description
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(descHeight.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(Color.White)
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column{
                                // Plant name
                        Text(
                            text = "Monsetra Adansonii",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                                // Plant family
                        Text(
                            text = "Monstera family",
                            fontSize = 15.sp,
                            color = LightGrey
                        )
                        
                        Spacer(modifier = Modifier.height(10.dp))

                        // Plant Price
                        Text(
                            text = "$19.00",
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp,
                            color = DarkGreen
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.heart2),
                        contentDescription = "",
                    )
                }
            }
        }

        // Add to cart

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp)
            .align(Alignment.BottomCenter)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Beige3)
                        .clickable {}
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(painter = painterResource(id = R.drawable.baseline_analytics_24), contentDescription = "")
                }

                Spacer(Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(LightGreen4)
                        .weight(5f)
                        .clickable {}
                        .padding(10.dp)
                    ,
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Add to Cart",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }

            }
        }
    }

}