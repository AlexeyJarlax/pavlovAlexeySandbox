package com.pavlovalexey.pavlovAlexeySandbox.repository

import com.pavlovalexey.pavlovAlexeySandbox.model.Workout

interface WorkoutRepository {
    suspend fun getWorkouts(): List<Workout>
    suspend fun getWorkoutById(id: Int): Workout
    suspend fun getWorkoutVideoLink(id: Int): String
}