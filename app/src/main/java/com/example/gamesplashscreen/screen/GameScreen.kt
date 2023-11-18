package com.example.gamesplashscreen.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gamesplashscreen.Problem
import kotlin.random.Random

@Composable
fun GameScreen(navController: NavController, level: String){
    Log.d("problem:", ProblemMaker(level).toString())
    Log.d("problem:", "${(12/16).toDouble()}")
}

fun ProblemMaker(level: String): Problem {
    var ishoralar = mutableListOf<String>("+", "-", "*", "/")
    var ishora = ishoralar.random()
    var result:Double = 0.0
    var problem = ""
    var numbers = NumberRandomizer(level)
    when(ishora) {
        "+" -> {
            problem = "${numbers.get(0)} + ${numbers.get(1)}"
            result = (numbers.get(0) + numbers.get(1)).toDouble()
        }
        "-" -> {
            problem = "${numbers.get(0)} - ${numbers.get(1)}"
            result = (numbers.get(0) - numbers.get(1)).toDouble()
        }
        "*" -> {
            problem = "${numbers.get(0)} * ${numbers.get(1)}"
            result = (numbers.get(0) * numbers.get(1)).toDouble()
        }
        "/" -> {
            problem = "${numbers.get(0)} / ${numbers.get(1)}"
            result = (numbers.get(0).toDouble() / numbers.get(1)).toDouble()
        }
    }
    if (result.toString().length > 3){

    }
    return Problem(problem, result)
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
