package com.example.core.data.di

import com.example.core.data.repository.CatsRepository
import com.example.core.data.repository.CatsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsCatsRepository(catsRepository: CatsRepositoryImpl): CatsRepository
}
