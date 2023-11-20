package com.example.gamesplashscreen.screen

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import java.util.concurrent.TimeUnit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamesplashscreen.Problem
import kotlin.random.Random
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.gamesplashscreen.datastore.ScoreStore
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Collections
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamesplashscreen.screen.TimeFormatExt.timeFormat
import com.example.gamesplashscreen.tools.CountDownTimerViewModel
import com.example.gamesplashscreen.tools.ProblemMaker


@Composable
fun GameScreen(navController: NavController, level: String, viewModel: CountDownTimerViewModel = viewModel()){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = ScoreStore(context)
    var prob = ProblemMaker(level)
    val savedScore = dataStore.getScore.collectAsState(initial = 0)
    var problem = remember {
        mutableStateOf(prob.problem)
    }
    var options = remember {
        mutableStateOf(prob.options)
    }
    var score = remember {
        mutableStateOf(0)
    }
    var max_prob = remember {
        mutableStateOf(10)
    }
    viewModel.apply {
        Column(
        modifier = Modifier
            .background(Color.Magenta)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            startCountDownTimer(navController)
            Text(text = "Qolgan vaqt: ${timerText.value}", fontSize = 28.sp)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = problem.value,
                    modifier = Modifier.padding(0.dp, 10.dp),
                    color = Color.White,
                    fontSize = 36.sp
                )
                options.value.forEach { entry ->
                    Button(modifier = Modifier.padding(0.dp, 6.dp), onClick = {
                        if (entry.key == 0) {
                            score.value += 10
                            if (max_prob.value != 1) {
                                max_prob.value--
                                prob = ProblemMaker(level)
                                problem.value = prob.problem
                                options.value = prob.options
                            } else if(max_prob.value == 1){
                                if (savedScore.value < score.value) {
                                    scope.launch {
                                        dataStore.saveScore(score.value)
                                    }
                                }
                                navController.navigate("score_screen")
                            }
                        } else {
                            if (max_prob.value != 1) {
                                max_prob.value--
                                prob = ProblemMaker(level)
                                problem.value = prob.problem
                                options.value = prob.options
                            } else if(max_prob.value == 1 ){
                                if (savedScore.value < score.value) {
                                    scope.launch {
                                        dataStore.saveScore(score.value)
                                    }
                                }
                                navController.navigate("score_screen")
                            }
                        }
                    }) {
                        Text(text = entry.value, color = Color.White, fontSize = 30.sp)
                    }
                }
            }
        }
    }
}


object TimeFormatExt {
    private const val FORMAT = "%02d:%02d"

    fun Long.timeFormat(): String = String.format(
        FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this) % 60,
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}

