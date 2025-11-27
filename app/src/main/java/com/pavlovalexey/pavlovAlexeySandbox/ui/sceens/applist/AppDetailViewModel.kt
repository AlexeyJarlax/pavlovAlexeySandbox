package com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.applist

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlovalexey.pavlovAlexeySandbox.model.AppDetails
import com.pavlovalexey.pavlovAlexeySandbox.repository.InstalledAppsRepository
import com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    private val repository: InstalledAppsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val packageName: String = checkNotNull(savedStateHandle["packageName"])

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _details = MutableStateFlow<AppDetails?>(null)
    val details: StateFlow<AppDetails?> = _details

    init {load()}

    private fun load() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                _details.value = repository.getAppDetails(packageName)
                _uiState.value = UiState.Success()
            } catch (e: Exception) {
                _uiState.value =
                    UiState.Error(e.message ?: "Не удалось загрузить информацию")
            }
        }
    }
}