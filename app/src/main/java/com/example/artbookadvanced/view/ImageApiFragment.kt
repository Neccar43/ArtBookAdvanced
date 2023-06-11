package com.example.artbookadvanced.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artbookadvanced.R
import com.example.artbookadvanced.adapter.ImageAdapter
import com.example.artbookadvanced.databinding.FragmentImageApiBinding
import com.example.artbookadvanced.util.Status
import com.example.artbookadvanced.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImageApiFragment @Inject constructor(private val imageAdapter: ImageAdapter) :
    Fragment(R.layout.fragment_image_api) {
    private lateinit var binding: FragmentImageApiBinding
    private lateinit var viewModel: ArtViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageApiBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        subscribeToObservers()

        binding.imageRecyclerView.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
        var job:Job?=null
        binding.searchText.addTextChangedListener {
            job?.cancel()
            job=lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isEmpty()){
                        viewModel.searchForImage(it.toString())
                    }
                }
            }
        }
    }

    private fun subscribeToObservers() {
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map { imageResult ->
                        imageResult.previewURL
                    }
                    imageAdapter.images = urls ?: listOf()
                    binding.progressBar.visibility = View.GONE
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE

                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_SHORT)
                        .show()
                    binding.progressBar.visibility = View.GONE

                }

            }
        })
    }
}