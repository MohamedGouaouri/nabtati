package com.example.learn.nabtati.presentation.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learn.nabtati.presentation.ui.theme.DarkGreen
import com.example.learn.nabtati.presentation.ui.theme.LightGrey

@Composable
fun PlantCard(
    imageResource: Painter,
    name: String,
    family: String,
    price: Double,
    onClick: () -> Unit
) {
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
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    // Plant family
                    Text(
                        text = family,
                        fontSize = 15.sp,
                        color = LightGrey
                    )

                    // Plant Price
                    Text(
                        text = "$$price",
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                        color = DarkGreen
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}