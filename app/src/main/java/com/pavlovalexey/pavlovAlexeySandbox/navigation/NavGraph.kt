package com.pavlovalexey.pavlovAlexeySandbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.start.StartScreen
import com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.workoutDetail.WorkoutDetailScreen
import com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.player.VideoPlayerScreen
import androidx.annotation.Keep
import com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.applist.AppDetailScreen

@Keep
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController    = navController,
        startDestination = "start_screen",
        modifier         = modifier
    ) {
        composable("start_screen") {
            StartScreen(
                onWorkoutClick = { id ->
                    navController.navigate("workout_detail/$id")
                },
                onAppClick = { packageName ->
                    navController.navigate("app_detail/$packageName")
                }
            )
        }

        composable(
            route = "workout_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { back ->
            val id = back.arguments!!.getInt("id")
            WorkoutDetailScreen(
                workoutId    = id,
                navController = navController
            )
        }
        composable(
            route = "video_player?videoUrl={videoUrl}",
            arguments = listOf(
                navArgument("videoUrl") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { back ->
            val url = back.arguments?.getString("videoUrl").orEmpty()
            if (url.isNotEmpty()) {
                VideoPlayerScreen(
                    videoUrl = url,
                    onBack   = { navController.popBackStack() }
                )
            }
        }
        composable(
            route = "app_detail/{packageName}",
            arguments = listOf(
                navArgument("packageName") {
                    type = NavType.StringType
                }
            )
        ) {
            AppDetailScreen(navController = navController)
        }
    }
}