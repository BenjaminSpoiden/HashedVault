package com.ben.hashedvault.network

import com.ben.hashedvault.model.BasicModel

interface HashedVaultService {

    suspend fun onFetchData(): BasicModel
}