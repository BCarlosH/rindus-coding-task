package com.example.core.data.repository

import com.example.core.data.utils.Result
import com.example.core.model.Cat
import com.example.core.network.model.NetworkCat
import com.example.core.network.restApi.NetworkCatsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val networkCatsDataSource: NetworkCatsDataSource,
) : CatsRepository {

    override suspend fun getCats(): Flow<Result<List<Cat>>> {
        return flow<Result<List<Cat>>> {
            emit(Result.Success(networkCatsDataSource.getCats().asCat()))
        }
            .catch { emit(Result.Error(it)) }
            .flowOn(Dispatchers.IO)
    }
}

private fun List<NetworkCat>.asCat(): List<Cat> {
    return map {
        Cat(
            createdAt = it.createdAt,
            id = it.id,
            tags = it.tags,
            url = it.url
        )
    }
}
