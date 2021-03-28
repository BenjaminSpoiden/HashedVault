package com.ben.hashedvault.repository

import android.util.Log
import com.ben.hashedvault.network.HashedVaultService
import javax.inject.Inject

class HashedVaultRepository (private val hashedVaultService: HashedVaultService) {

    suspend fun onFetchData() = hashedVaultService.onFetchData()
}