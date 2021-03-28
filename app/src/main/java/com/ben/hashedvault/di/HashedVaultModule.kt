package com.ben.hashedvault.di

import android.content.Context
import com.ben.hashedvault.HashedVaultApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object HashedVaultModule {

    @Singleton
    @Provides
    fun onProvideApplication(@ApplicationContext app: Context): HashedVaultApplication {
        return app as HashedVaultApplication
    }
}