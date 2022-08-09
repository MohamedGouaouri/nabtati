package com.example.learn.nabtati.presentation.components.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.learn.R
import com.example.learn.nabtati.domain.model.Plant
import com.example.learn.nabtati.presentation.components.home.viewmodels.PlantsListViewModel
import com.example.learn.nabtati.presentation.ui.theme.DarkGreen
import com.example.learn.nabtati.presentation.ui.theme.LightGrey

@Composable
fun PlantCard(
    imageResource: Painter,
    plant: Plant,
    viewModel: PlantsListViewModel,
    onClick: () -> Unit,
) {

    val context = LocalContext.current

    var savedPlantState by remember {
        mutableStateOf(false)
    }


    Column(
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .clickable {
                    onClick()
                }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = imageResource,
                    contentDescription = "plant",
                    modifier = Modifier.size(100.dp, 100.dp)
                )

                Column(

                ) {
                    // Plant name
                    Text(
                        text = plant.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    // Plant family
                    Text(
                        text = plant.family,
                        fontSize = 15.sp,
                        color = LightGrey
                    )

                    // Plant Price
                    Text(
                        text = "$${plant.price}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                        color = DarkGreen
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.heart2),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable {
                            savedPlantState = if (savedPlantState) {
                                viewModel.deletePlant(context, plant)
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                                false

                            } else{
                                viewModel.savePlant(context, plant)
                                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                true
                            }

                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}