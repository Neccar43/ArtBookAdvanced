package com.example.artbookadvanced.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.artbookadvanced.databinding.ImageRowBinding
import com.example.artbookadvanced.roomdb.Art
import javax.inject.Inject

class ImageAdapter @Inject constructor(private val glide: RequestManager): RecyclerView.Adapter<ImageAdapter.ImageHolder>() {
    class ImageHolder(val binding: ImageRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding=ImageRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageHolder(binding)
    }

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

     var images: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    private var onItemClickListener:((String)->Unit)?=null
     fun setOnItemClickListener(listener:(String)->Unit){
        onItemClickListener=listener
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val url =images[position]
        glide.load(url).into(holder.binding.singleArtImageView)
       holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(url)
            }
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }
}