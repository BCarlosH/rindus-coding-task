package com.example.feature.cats

import com.example.core.model.Cat

sealed interface CatsUiState {
    object Loading : CatsUiState
    data class Success(val cats: List<Cat>) : CatsUiState
    data class Error(val errorMessage: String) : CatsUiState
}
