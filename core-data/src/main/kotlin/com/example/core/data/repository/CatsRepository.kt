package com.example.core.data.repository

import com.example.core.data.utils.Result
import com.example.core.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    suspend fun getCats(): Flow<Result<List<Cat>>>
    suspend fun getCatById(catId: String): Flow<Result<Cat>>
}
