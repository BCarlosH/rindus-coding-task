package com.rinduscodingtask.core.data.di

import android.content.Context
import com.rinduscodingtask.core.data.errorHandler.ErrorHandler
import com.rinduscodingtask.core.data.errorHandler.ErrorHandlerImpl
import com.rinduscodingtask.core.data.repository.WeatherRepository
import com.rinduscodingtask.core.data.repository.WeatherRepositoryImpl
import com.rinduscodingtask.core.data.utils.ResourceProvider
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
    fun bindsWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository

    companion object {

        @Provides
        internal fun providesResourceProvider(@ApplicationContext context: Context) =
            ResourceProvider(context)
    }
}
