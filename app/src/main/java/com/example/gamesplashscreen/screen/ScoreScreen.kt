package com.example.gamesplashscreen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gamesplashscreen.R
import com.example.gamesplashscreen.datastore.ScoreStore
import kotlin.system.exitProcess

@Composable
fun ScoreScreen(navController: NavController){
    val context = LocalContext.current
    val dataStore = ScoreStore(context)
    val savedScore = dataStore.getScore.collectAsState(initial = 0)
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 30.sp, color = Color.Blue, shadow = Shadow(
                        color = Color.Blue, offset = Offset(5.0f, 10.0f), blurRadius = 3f
                    )
                    )) {
                        append("Sizning hozirgacha golingiz:")
                    }

                    withStyle(style = SpanStyle(fontSize = 28.sp, color = Color.Red)) {
                        append("\n${savedScore.value}")
                    }
                },
                textAlign = TextAlign.Center
            )
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

