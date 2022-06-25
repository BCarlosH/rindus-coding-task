package com.example.core.data.repository

import com.example.core.model.Cats
import com.example.core.network.restApi.CatsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val catsService: CatsService,
) : CatsRepository {

    override suspend fun getCats(): Flow<List<Cats>> {
        // TODO: implement catsService call
    }
}
