package com.ben.hashedvault.di

import com.ben.hashedvault.BuildConfig
import com.ben.hashedvault.network.HashedVaultService
import com.ben.hashedvault.repository.HashedVaultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HashedVaultNetworkModule {

    @Singleton
    @Provides
    fun onProvideRetrofitService(): HashedVaultService {
        return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().apply {
                    if(BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        this.addInterceptor(logging)
                    }
                }.build())
                .build()
                .create(HashedVaultService::class.java)
    }

    @Singleton
    @Provides
    fun onProvideAuthToken(accessToken: String) = "Token $accessToken"
}