package com.ben.hashedvault.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ben.hashedvault.repository.HashedVaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val hashedVaultRepository: HashedVaultRepository): ViewModel() {

    fun onTest() {
        Log.d("Tag", "Testing VM: $hashedVaultRepository")
    }

    fun onHash(text: String, algorithm: String): String {
        val bytes = MessageDigest.getInstance(algorithm).digest(text.toByteArray())
        return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray): String = byteArray.joinToString("") {
        "%02x".format(it)
    }
}