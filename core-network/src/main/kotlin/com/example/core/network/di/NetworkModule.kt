package com.example.core.network.di

import android.content.Context
import com.example.core.network.interceptors.ConnectivityInterceptor
import com.example.core.network.restApi.NetworkCatsDataSource
import com.example.core.network.restApi.NetworkCatsDataSourceImpl
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
    fun bindsNetworkCatsDataSource(
        networkCatsDataSource: NetworkCatsDataSourceImpl,
    ): NetworkCatsDataSource

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
