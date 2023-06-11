package com.example.artbookadvanced.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artbookadvanced.R
import com.example.artbookadvanced.adapter.ArtAdapter
import com.example.artbookadvanced.databinding.FragmentFragmentsArtBinding
import com.example.artbookadvanced.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class ArtFragment @Inject constructor(private val artAdapter: ArtAdapter) :
    Fragment(R.layout.fragment_fragments_art) {
    lateinit var viewModel:ArtViewModel
    private lateinit var binding: FragmentFragmentsArtBinding

    private val swipeCallBack=object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            //bu durumda bir şey yapmasın
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            //kaydırma işlemi gerçekleşince item'ın silinmesini istiyoruz
            val position=viewHolder.layoutPosition
            val selectedArt=artAdapter.artList[position]
            viewModel.deleteArt(selectedArt)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(requireActivity())[ArtViewModel::class.java]

        binding = FragmentFragmentsArtBinding.bind(view)

        subscribeToObservers()

        binding.fab.setOnClickListener {
            val action = ArtFragmentDirections.actionArtFragmentToArtDetailsFragment()
            findNavController().navigate(action)
        }
        binding.recyclerViewArt.apply {
            adapter=artAdapter
            layoutManager=LinearLayoutManager(requireContext())

        }
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewArt)


    }

    private fun subscribeToObservers() {
        viewModel.artList.observe(viewLifecycleOwner, Observer {
            artAdapter.artList=it
        })
    }
}