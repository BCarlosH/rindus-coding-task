package com.example.feature.cats.catImage

import com.example.core.model.Cat

sealed interface CatImageUiState {
    object Loading : CatImageUiState
    data class Success(val cat: Cat) : CatImageUiState
    data class Error(val errorMessage: String) : CatImageUiState
}
