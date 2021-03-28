package com.ben.hashedvault.ui.home

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.navigation.fragment.navArgs
import com.ben.hashedvault.databinding.FragmentBottomSheetBinding
import com.ben.hashedvault.interfaces.CopyListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        var copyListener: CopyListener? = null
    }

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val args: BottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            setupBottomSheet(it)
        }

        return dialog
    }

    private fun setupBottomSheet(dialogInterface: DialogInterface) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet)
            ?: return
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressIndicator.max = 10000
        binding.copyButton.setOnClickListener {
            onCopyToClipboard(args.hashedValue).also {
                copyListener?.onCopiedElementListener()
                onDestroyView()
            }
        }
        ObjectAnimator.ofInt(binding.progressIndicator, "progress", 10000)
            .setDuration(8000).apply {
                this.doOnEnd {
                    onDestroyView()
                }
            }
            .start()
    }

    private fun onCopyToClipboard(hashedValue: String) {
        val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Hashed Value", hashedValue)
        clipboardManager.setPrimaryClip(clipData)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}