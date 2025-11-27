package com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.applist

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlovalexey.pavlovAlexeySandbox.model.InstalledApp
import com.pavlovalexey.pavlovAlexeySandbox.repository.InstalledAppsRepository
import com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InstalledAppsViewModel @Inject constructor(
    private val repository: InstalledAppsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _apps = MutableStateFlow<List<InstalledApp>>(emptyList())
    val apps: StateFlow<List<InstalledApp>> = _apps

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                _apps.value = repository.getInstalledApps()
                _uiState.value = UiState.Success()
            } catch (e: Exception) {
                _uiState.value =
                    UiState.Error(e.message ?: "Не удалось загрузить список приложений")
            }
        }
    }
}