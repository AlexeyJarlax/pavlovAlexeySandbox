package com.pavlovalexey.startsetupforcomposein2024.utils.permissions

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.MutableState

class PermissionManager internal constructor(
    private val context: Context,
    private val activity: ComponentActivity,
    private val launcherState: MutableState<PermissionRequest?>,
    private val launcher: ActivityResultLauncher<Array<String>>
) {
    fun requestPermissions(
        permissions: Array<String>,
        onGranted: () -> Unit,
        onDenied: (PermissionDeniedState) -> Unit = {}
    ) {
        if (context.hasAllPermissions(permissions)) {
            onGranted()
            return
        }

        launcherState.value = PermissionRequest(permissions, onGranted, onDenied)
        launcher.launch(permissions)
    }
}

data class PermissionDeniedState(
    val permissions: Array<String>,
    val permanentlyDenied: Boolean
)

internal data class PermissionRequest(
    val permissions: Array<String>,
    val onGranted: () -> Unit,
    val onDenied: (PermissionDeniedState) -> Unit
)
