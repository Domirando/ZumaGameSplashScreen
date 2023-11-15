package com.example.gamesplashscreen.screen

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gamesplashscreen.R
import com.example.gamesplashscreen.screen.ui.theme.GameSplashScreenTheme
@Composable
fun GameScreen(navController: NavController){
    Column (
        modifier = Modifier
            .background(Color.Magenta)
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "9 x 5 = 45", color = Color.White, fontSize = 30.sp)
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(onClick = { /*TODO*/ }) {
                Text(text = "*", color = Color.White, fontSize = 30.sp)
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "+", color = Color.White, fontSize = 30.sp)
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "-", color = Color.White, fontSize = 30.sp)
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "/", color = Color.White, fontSize = 30.sp)
            }
        }
    }
}
