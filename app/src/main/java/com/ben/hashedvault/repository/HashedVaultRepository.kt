package com.ben.hashedvault.repository

import com.ben.hashedvault.network.HashedVaultService
import javax.inject.Inject

class HashedVaultRepository @Inject constructor(private val hashedVaultService: HashedVaultService) {

    suspend fun onFetchData() = hashedVaultService.onFetchData()
}