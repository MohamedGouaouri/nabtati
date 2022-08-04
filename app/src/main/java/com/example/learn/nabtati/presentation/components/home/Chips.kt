package com.example.learn.nabtati.presentation.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.learn.nabtati.presentation.ui.theme.LightGreen4
import com.example.learn.nabtati.presentation.ui.theme.LightGrey


@Composable
fun Chips(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    var selectedItem by remember {
        mutableStateOf(0)
    }

    LazyRow(
        modifier = modifier
    ){

        itemsIndexed(items){index, item->
            ChipItem(content = item, isSelected = index == selectedItem) {
                selectedItem = index

                // Fetch new data and put it in
            }
        }
    }
}


@Composable
fun ChipItem(
    content: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {

    if (!isSelected){
        Text(
            text = content,
            color = LightGrey,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clickable {
                    onClick()
                }
        )
    }else{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = content,
                color = LightGreen4,
                fontWeight= FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clickable {
                        onClick()
                    }
            )
            // TODO: Draw point
            Canvas(modifier = Modifier){
                drawCircle(LightGreen4, radius = 10f)
            }
        }
    }

}