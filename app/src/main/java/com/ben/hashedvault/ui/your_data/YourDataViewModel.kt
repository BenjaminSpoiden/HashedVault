package com.ben.hashedvault.ui.your_data

import androidx.lifecycle.ViewModel
import com.ben.hashedvault.repository.HashedVaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YourDataViewModel @Inject constructor(private val hashedVaultRepository: HashedVaultRepository) :  ViewModel() {
}