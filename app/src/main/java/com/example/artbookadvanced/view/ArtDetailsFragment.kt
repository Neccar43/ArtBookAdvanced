package com.example.artbookadvanced.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.artbookadvanced.R
import com.example.artbookadvanced.databinding.FragmentArtDetailBinding
import com.example.artbookadvanced.databinding.FragmentFragmentsArtBinding

class ArtDetailsFragment : Fragment(R.layout.fragment_art_detail) {
    private lateinit var binding: FragmentArtDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtDetailBinding.bind(view)

        binding.artImageView.setOnClickListener {
            val action = ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
            findNavController().navigate(action)
        }

        //geri tuşuna basıldığında yapılacak işlemleri manuel olarak belirliyoruz
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }
}
