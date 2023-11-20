package com.example.gamesplashscreen.tools

import kotlin.random.Random

fun NumberRandomizer(level:String):List<Int>{
    if (level == "easy"){
        return  List(2) { Random.nextInt(1, 30) }
    } else if (level == "medium"){
        return  List(2) { Random.nextInt(40, 80) }
    } else {
        return  List(2) { Random.nextInt(90, 300) }
    }
}
