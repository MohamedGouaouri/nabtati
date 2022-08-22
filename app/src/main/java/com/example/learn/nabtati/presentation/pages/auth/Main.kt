package com.example.learn.nabtati.presentation.components.auth

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.learn.R
import com.example.learn.nabtati.presentation.MainActivity
import com.example.learn.nabtati.presentation.ui.theme.*

@Composable
fun AuthMain(
    ctx: Context,
    activityKiller : () -> Unit
) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Card(
            modifier = Modifier
                .size(6f * screenWidth / 7, 4f * screenHeight / 5)
                .clip(RoundedCornerShape(40.dp))
                .background(Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.leaf),
                        contentDescription = "",
                        modifier = Modifier.height(150.dp)
                    )
                    Text(
                        text = "Nabtati",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic
                    )
                }


                Text(
                    text = buildAnnotatedString {
                        append("Plant a ")
                        withStyle(
                            style = SpanStyle(
                                color = LightGreen4,
                                fontStyle = FontStyle.Italic
                            )
                        ){
                            append("tree")
                        }
                        append(", ")
                        withStyle(
                            style = SpanStyle(
                                color = LightGreen4,
                                fontStyle = FontStyle.Italic
                            )
                        ){
                            append("green ")
                        }
                        append("the earth")
                    },
                    fontSize = 44.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )


                // Button and link
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // sign in button
                        SignInBtn(
                            ctx,
                            activityKiller
                        )
                        Text(
                            text = "Create an account",
                            color = LightGrey,
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 30.dp)
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun SignInBtn(
    ctx: Context,
    activityKiller: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(DarkGreen)
            .clickable {
                val intent = Intent(ctx, MainActivity::class.java)
                ctx.startActivity(intent)
                // Kills auth activity
                activityKiller()
            }

    ) {
        Text(
            text = "Sign In",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }
}