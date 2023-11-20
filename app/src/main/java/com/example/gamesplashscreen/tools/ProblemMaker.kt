package com.example.gamesplashscreen.tools

import com.example.gamesplashscreen.Problem
import java.util.Collections

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
