package com.ben.hashedvault.di

import com.ben.hashedvault.network.HashedVaultService
import com.ben.hashedvault.repository.HashedVaultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HashedVaultRepositoryModule {

    @Singleton
    @Provides
    fun onProvideRepository(hashedVaultService: HashedVaultService) = HashedVaultRepository(hashedVaultService)
}