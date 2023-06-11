package com.example.artbookadvanced.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbookadvanced.databinding.ArtRowBinding
import com.example.artbookadvanced.databinding.FragmentArtDetailBinding
import com.example.artbookadvanced.roomdb.Art
import javax.inject.Inject

class ArtAdapter @Inject constructor(private val glide: RequestManager) :
    RecyclerView.Adapter<ArtAdapter.ArtViewHolder>() {
    class ArtViewHolder(val binding: ArtRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val binding = ArtRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtViewHolder(binding)
    }

    //notifyDataSetChange yerine bunu kullanmak daha verimli
    private val diffUtil = object : DiffUtil.ItemCallback<Art>() {
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var artList: List<Art>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val art = artList[position]
        holder.binding.apply {
            artRowArtNameText.text = "Name:${art.name}"
            artRowArtNameText.text = "Artist Name:${art.artistName}"
            artRowArtNameText.text = "Year:${art.year}"
        }
        glide.load(art.imgUrl).into(holder.binding.artRowImageView)
    }

    override fun getItemCount(): Int {
        return artList.size
    }
}