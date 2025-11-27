package com.pavlovalexey.startsetupforcomposein2024.utils.permissions

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

fun Context.hasPermission(perm: String): Boolean =
    ContextCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED

fun Context.hasAllPermissions(perms: Array<String>): Boolean =
    perms.all { hasPermission(it) }

fun requiredGalleryPermissions(): Array<String> =
    if (Build.VERSION.SDK_INT >= 33) {
        arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
    } else {
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

fun requiredCameraPermissions(): Array<String> =
    arrayOf(Manifest.permission.CAMERA)

fun requiredLocationPermissions(): Array<String> =
    arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

fun requiredInstalledAppsPermissions(): Array<String> = emptyArray()