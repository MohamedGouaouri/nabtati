package com.example.learn.nabtati.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import com.example.learn.nabtati.presentation.components.auth.AuthMain

class AuthActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold {
                AuthMain(
                    ctx = this,
                    activityKiller = activityKiller
                )
            }
        }
    }

    private val activityKiller: () -> Unit = {
        this.finish()
    }
}