package com.ben.hashedvault.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ben.hashedvault.repository.HashedVaultRepository
import com.ben.hashedvault.ui.home.HomeViewModel
import com.ben.hashedvault.ui.your_data.YourDataViewModel
import java.lang.Exception
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val hashedVaultRepository: HashedVaultRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(hashedVaultRepository) as T
            modelClass.isAssignableFrom(YourDataViewModel::class.java) -> YourDataViewModel(hashedVaultRepository) as T
            else -> throw Exception("View model doesn't exist")
        }
    }
}