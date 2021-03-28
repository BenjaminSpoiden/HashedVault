package com.ben.hashedvault.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ben.hashedvault.R
import com.ben.hashedvault.databinding.FragmentHomeBinding
import com.ben.hashedvault.interfaces.CopyListener
import com.ben.hashedvault.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), CopyListener {

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        setHasOptionsMenu(true)
        return FragmentHomeBinding.inflate(inflater, container, false)
    }


    override fun bindViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val algorithms = resources.getStringArray(R.array.algorithms)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, algorithms)
        binding.hashTypeSelection.setAdapter(arrayAdapter)
        BottomSheetFragment.copyListener = this
        binding.generateHashBtn.setOnClickListener {
            onHash().also {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBottomSheetFragment(it))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.clear_menu) {
            binding.plainTextInput.text.clear()
            return true
        }
        return true
    }

    private fun onHash(): String {
        val algorithm = binding.hashTypeSelection.text.toString()
        val inputText = binding.plainTextInput.text.toString()
        return viewModel.onHash(inputText, algorithm)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
    }

    override fun onCopiedElementListener() {
        lifecycleScope.launch {
            applyCopyAnimation()
        }
    }

    override fun onDestroyView() {
        BottomSheetFragment.copyListener = null
        super.onDestroyView()
    }

    private suspend fun applyCopyAnimation() {
        binding.includeCopiedMessage.bgMessage.animate().translationY(110f).duration = 200L
        binding.includeCopiedMessage.copiedMessage.animate().translationY(110f).duration = 200L

        delay(2000L)

        binding.includeCopiedMessage.bgMessage.animate().translationY(-110f).duration = 500L
        binding.includeCopiedMessage.copiedMessage.animate().translationY(-110f).duration = 500L
    }
}