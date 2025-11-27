package com.pavlovalexey.startsetupforcomposein2024.utils.permissions

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat

@Composable
fun rememberPermissionManager(activity: ComponentActivity): PermissionManager {
    val context = activity
    val pendingRequest = remember { mutableStateOf<PermissionRequest?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val request = pendingRequest.value ?: return@rememberLauncherForActivityResult
        pendingRequest.value = null

        val allGranted = request.permissions.all { perm -> result[perm] == true }
        if (allGranted) {
            request.onGranted()
        } else {
            val permanentlyDenied = request.permissions.any { perm ->
                result[perm] != true &&
                        !ActivityCompat.shouldShowRequestPermissionRationale(activity, perm)
            }
            request.onDenied(PermissionDeniedState(request.permissions, permanentlyDenied))
        }
    }

    return remember(activity) {
        PermissionManager(context, activity, pendingRequest, launcher)
    }
}