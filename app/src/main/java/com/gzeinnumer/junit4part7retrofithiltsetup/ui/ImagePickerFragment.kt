package com.gzeinnumer.junit4part7retrofithiltsetup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gzeinnumer.junit4part7retrofithiltsetup.R

//todo 4
class ImagePickerFragment : Fragment(R.layout.fragment_image_picker) {

    //todo 12
    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
    }

}