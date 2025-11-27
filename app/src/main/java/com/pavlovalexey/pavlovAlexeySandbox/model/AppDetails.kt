package com.pavlovalexey.pavlovAlexeySandbox.model

/** Павлов Алексей https://github.com/AlexeyJarlax */

data class AppDetails(
    val appName: String,
    val packageName: String,
    val versionName: String?,
    val versionCode: Long?,
    val apkChecksumSha256: String?
)