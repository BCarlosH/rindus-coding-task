package com.example.feature.cats

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
class CatsViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
) : ViewModel() {

    private val _catsUiState: MutableStateFlow<CatsUiState> =
        MutableStateFlow(CatsUiState.Loading)
    val catsUiState: StateFlow<CatsUiState> = _catsUiState

    init {
        fetchCats()
    }

    private fun fetchCats() {
        viewModelScope.launch {
            catsRepository.getCats()
                .collect { result ->
                    _catsUiState.value = when (result) {
                        is Result.Success -> CatsUiState.Success(result.data)
                        is Result.Error -> CatsUiState.Error(result.errorMessage)
                    }
                }
        }
    }
}
