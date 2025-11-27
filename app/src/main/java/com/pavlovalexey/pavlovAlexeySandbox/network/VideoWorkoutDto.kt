package com.pavlovalexey.pavlovAlexeySandbox.network
import androidx.annotation.Keep

@Keep
data class VideoWorkoutDto(
    val id: Int,
    val duration: String,
    val link: String
)