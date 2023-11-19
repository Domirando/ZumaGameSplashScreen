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
                            if (max_prob.value != 1 && isPlaying.value) {
                                max_prob.value--
                                prob = ProblemMaker(level)
                                problem.value = prob.problem
                                options.value = prob.options
                            } else {
                                if (savedScore.value < score.value) {
                                    scope.launch {
                                        dataStore.saveScore(score.value)
                                    }
                                }
                                navController.navigate("score_screen")
                            }
                        } else {
                            if (max_prob.value != 1 && isPlaying.value) {
                                max_prob.value--
                                prob = ProblemMaker(level)
                                problem.value = prob.problem
                                options.value = prob.options
                            } else {
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

fun ProblemMaker(level: String): Problem {
    var ishoralar = mutableListOf<String>("+", "-", "*", "/")
    var ishora = ishoralar.random()
    var result:Float = 0f
    var problem = ""
    val options = LinkedHashMap<Int, String>()
    var numbers = NumberRandomizer(level)
    when(ishora) {
        "+" -> {
            problem = "${numbers.get(0)} + ${numbers.get(1)}"
            result = (numbers.get(0) + numbers.get(1)).toFloat()
        }
        "-" -> {
            problem = "${numbers.get(0)} - ${numbers.get(1)}"
            result = (numbers.get(0) - numbers.get(1)).toFloat()
        }
        "*" -> {
            problem = "${numbers.get(0)} * ${numbers.get(1)}"
            result = (numbers.get(0) * numbers.get(1)).toFloat()
        }
        "/" -> {
            problem = "${numbers.get(0)} / ${numbers.get(1)}"
            result = (numbers.get(0).toFloat() / numbers.get(1)).toFloat()
            if(result.toString().length > 3){
                result = result.toString().substring(0, 4).toFloat()
            }
        }
    }
    options[0] = result.toString()
    for (i in 1..3){
        options[i] = NumberRandomizer(level)[0].toFloat().toString()
    }
    val list = options.toList()

    // Shuffle the list
    Collections.shuffle(list)

    // Create a new LinkedHashMap from the shuffled list
    val shuffledMap = linkedMapOf(*list.toTypedArray())
    return Problem(problem, shuffledMap)
}
fun NumberRandomizer(level:String):List<Int>{
    if (level == "easy"){
        return  List(2) { Random.nextInt(1, 30) }
    } else if (level == "medium"){
        return  List(2) { Random.nextInt(40, 80) }
    } else {
        return  List(2) { Random.nextInt(90, 300) }
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

class CountDownTimerViewModel() : ViewModel() {

    private var countDownTimer: CountDownTimer? = null

    private val userInputMinutes = TimeUnit.MINUTES.toMillis(1)
    private val userInputSecond = TimeUnit.SECONDS.toMillis(60)

    val initialTotalTimeInMillis = userInputMinutes+userInputSecond
    var timeLeft = mutableStateOf(initialTotalTimeInMillis)
    val countDownInterval = 1000L // 1 seconds is the lowest

    val timerText = mutableStateOf(timeLeft.value.timeFormat())

    val isPlaying = mutableStateOf(false)

    fun startCountDownTimer(navController: NavController) = viewModelScope.launch {
        isPlaying.value = true
        countDownTimer = object : CountDownTimer(timeLeft.value, countDownInterval) {
            override fun onTick(currentTimeLeft: Long) {
                timerText.value = currentTimeLeft.timeFormat()
                timeLeft.value = currentTimeLeft
            }

            override fun onFinish() {
                timerText.value = initialTotalTimeInMillis.timeFormat()
                isPlaying.value = false
                navController.navigate("score_screen")

            }
        }.start()
    }

    fun stopCountDownTimer() = viewModelScope.launch {
        isPlaying.value = false
        countDownTimer?.cancel()
    }

    fun resetCountDownTimer() = viewModelScope.launch {
        isPlaying.value = false
        countDownTimer?.cancel()
        timerText.value = initialTotalTimeInMillis.timeFormat()
        timeLeft.value = initialTotalTimeInMillis
    }
}