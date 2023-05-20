package com.example.artbookadvanced.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.artbookadvanced.R
import com.example.artbookadvanced.databinding.FragmentFragmentsArtBinding

private lateinit var binding: FragmentFragmentsArtBinding
class ArtFragment : Fragment(R.layout.fragment_fragments_art) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding= FragmentFragmentsArtBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        binding.fab.setOnClickListener {
            val action=ArtFragmentDirections.actionArtFragmentToArtDetailsFragment()
            findNavController().navigate(action)
        }

    }
}