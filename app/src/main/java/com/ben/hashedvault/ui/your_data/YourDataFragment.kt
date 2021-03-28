package com.ben.hashedvault.ui.your_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ben.hashedvault.databinding.FragmentYourDataBinding
import com.ben.hashedvault.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YourDataFragment : BaseFragment<FragmentYourDataBinding, YourDataViewModel>() {

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            FragmentYourDataBinding = FragmentYourDataBinding.inflate(inflater, container, false)

    override fun bindViewModel(): Class<YourDataViewModel> = YourDataViewModel::class.java
}