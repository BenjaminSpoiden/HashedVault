package com.ben.hashedvault.view

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ben.hashedvault.R
import com.ben.hashedvault.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun bindFragment(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        setHasOptionsMenu(true)
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val algorithms = resources.getStringArray(R.array.algorithms)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, algorithms)
        binding.hashTypeSelection.setAdapter(arrayAdapter)

        binding.generateHashBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bottomSheetFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
    }
}