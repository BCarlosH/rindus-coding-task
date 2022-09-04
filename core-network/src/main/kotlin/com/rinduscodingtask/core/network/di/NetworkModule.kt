package com.rinduscodingtask.core.network.di

import android.content.Context
import com.rinduscodingtask.core.network.interceptors.ConnectivityInterceptor
import com.rinduscodingtask.core.network.restApi.NetworkWeatherDataSource
import com.rinduscodingtask.core.network.restApi.NetworkWeatherDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsNetworkWeatherDataSource(
        networkWeatherDataSource: NetworkWeatherDataSourceImpl
    ): NetworkWeatherDataSource

    companion object {

        @Provides
        internal fun providesConnectivityInterceptor(@ApplicationContext context: Context) =
            ConnectivityInterceptor(context)

        @Provides
        internal fun providesHttpLoggingInterceptor() =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        @Provides
        internal fun providesOkHttpClient(
            connectivityInterceptor: ConnectivityInterceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient =
            OkHttpClient
                .Builder()
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }
}
