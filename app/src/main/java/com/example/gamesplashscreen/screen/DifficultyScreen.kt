package com.example.gamesplashscreen.screen

import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gamesplashscreen.R
import com.example.gamesplashscreen.screen.ui.theme.GameSplashScreenTheme
import kotlinx.coroutines.delay
import kotlin.system.exitProcess

@Composable
fun DifficultyScreen(navController: NavController){
    var level = "easy"
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
                level = "easy"
                navController.navigate("game_screen/$level")
            }) {
                Text(text = "Oson daraja!")
            }
            Button(onClick = {
                level = "medium"
                navController.navigate("game_screen/$level")
            }) {
                Text(text = "O'rta daraja!")
            }
            Button(onClick = {
                level = "hard"
                navController.navigate("game_screen/$level")
            }) {
                Text(text = "Qiyin daraja!")
            }
        }
    }

}