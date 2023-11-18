package com.example.gamesplashscreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamesplashscreen.screen.DifficultyScreen
import com.example.gamesplashscreen.screen.EnterScreen
import com.example.gamesplashscreen.screen.GameScreen
import com.example.gamesplashscreen.screen.SplashScreen


@Composable
fun Nav() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable(route = "splash_screen"){
            SplashScreen(navController = navController)
        }
        composable(route = "game_screen/{level}"){ navBackStack ->
            val level = navBackStack.arguments?.getString("level")
            GameScreen(navController = navController, level = level.toString())
        }
        composable(route = "enter_screen"){
            EnterScreen(navController = navController)
        }
        composable(route = "difficulty_screen"){
            DifficultyScreen(navController = navController)
        }
    }
}
