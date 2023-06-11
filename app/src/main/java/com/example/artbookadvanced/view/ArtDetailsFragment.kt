package com.example.artbookadvanced.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbookadvanced.R
import com.example.artbookadvanced.databinding.FragmentArtDetailBinding
import com.example.artbookadvanced.databinding.FragmentFragmentsArtBinding
import com.example.artbookadvanced.util.Status
import com.example.artbookadvanced.viewmodel.ArtViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class ArtDetailsFragment @Inject constructor(private val glide: RequestManager) :
    Fragment(R.layout.fragment_art_detail) {
    private lateinit var binding: FragmentArtDetailBinding
    private lateinit var viewModel: ArtViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtDetailBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        subscribeToObservers()

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

        binding.saveButton.setOnClickListener {
            viewModel.makeArt(
                binding.nameText.text.toString(),
                binding.artistText.text.toString(),
                binding.yearText.text.toString()
            )
        }


    }

    private fun subscribeToObservers() {
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url ->
            glide.load(url).into(binding.artImageView)
        })
        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertArtMsg()
                }

                Status.LOADING -> {
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message?:"Error", Toast.LENGTH_SHORT).show()

                }

            }
        })
    }
}
