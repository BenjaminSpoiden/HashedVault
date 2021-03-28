package com.ben.hashedvault.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ben.hashedvault.repository.HashedVaultRepository
import com.ben.hashedvault.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.KClass


abstract class BaseFragment<VB: ViewBinding, VM: ViewModel> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    private var _viewModel: VM? = null
    val viewModel get() = _viewModel!!

    @Inject lateinit var hashedVaultRepository: HashedVaultRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindFragment(inflater, container, savedInstanceState)
        val viewModelFactory = ViewModelFactory(hashedVaultRepository)
        _viewModel = ViewModelProvider(this, viewModelFactory)[bindViewModel()]
        return binding.root
    }

    abstract fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): VB
    abstract fun bindViewModel(): Class<VM>

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}