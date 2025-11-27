package com.pavlovalexey.pavlovAlexeySandbox.ui.sceens

sealed class UiState {
    object Loading : UiState()
    data class Success(val message: String? = null) : UiState()
    data class Error(val message: String) : UiState()
}