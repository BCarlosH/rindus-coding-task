package com.example.feature.cats.catImage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.repository.CatsRepository
import com.example.core.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatImageViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
) : ViewModel() {

    private val _catImageUiState: MutableStateFlow<CatImageUiState> =
        MutableStateFlow(CatImageUiState.Loading)
    val catImageUiState: StateFlow<CatImageUiState> = _catImageUiState

    fun fetchCatById(catId: String) {
        viewModelScope.launch {
            catsRepository.getCatById(catId)
                .collect { result ->
                    _catImageUiState.value = when (result) {
                        is Result.Success -> CatImageUiState.Success(result.data)
                        is Result.Error -> CatImageUiState.Error(result.errorMessage)
                    }
                }
        }
    }
}
