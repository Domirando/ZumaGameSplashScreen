package com.example.gamesplashscreen.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gamesplashscreen.R
import com.example.gamesplashscreen.screen.ui.theme.GameSplashScreenTheme
import kotlin.system.exitProcess

@Composable
fun EnterScreen(navController: NavController){
    var modifier = Modifier
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
        ){
            Image(painter = painterResource(id = R.drawable.zuma_header), modifier = Modifier.scale(1.3f),
                contentDescription = "header")
        }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Button(onClick = {
                exitProcess(-1)
            }) {
                Text(text = "O'yindan chiqish!")
            }
            Button(onClick = {
                navController.navigate("difficulty_screen")
            }) {
                Text(text = "O'yinni davom ettirish!")
            }
        }
    }
}
