package com.example.core.data.di

import android.content.Context
import com.example.core.data.errorHandler.ErrorHandler
import com.example.core.data.errorHandler.ErrorHandlerImpl
import com.example.core.data.repository.CatsRepository
import com.example.core.data.repository.CatsRepositoryImpl
import com.example.core.data.utils.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsErrorHandler(errorHandler: ErrorHandlerImpl): ErrorHandler

    @Binds
    fun bindsCatsRepository(catsRepository: CatsRepositoryImpl): CatsRepository

    companion object {

        @Provides
        internal fun providesResourceProvider(@ApplicationContext context: Context) =
            ResourceProvider(context)
    }
}
