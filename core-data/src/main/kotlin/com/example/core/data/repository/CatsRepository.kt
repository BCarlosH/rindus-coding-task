package com.example.core.data.repository

import com.example.core.model.Cat
import com.example.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    suspend fun getCats(): Flow<Result<List<Cat>>>
}
