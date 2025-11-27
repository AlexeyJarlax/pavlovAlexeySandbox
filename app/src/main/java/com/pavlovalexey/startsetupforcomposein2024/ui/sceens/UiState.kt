package com.pavlovalexey.startsetupforcomposein2024.ui.sceens

sealed class UiState {
    object Loading : UiState()
    data class Success(val message: String? = null) : UiState()
    data class Error(val message: String) : UiState()
}