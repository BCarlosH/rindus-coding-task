package com.example.core.data.repository

import com.example.core.model.Cats
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    suspend fun getCats(): Flow<List<Cats>>
}
