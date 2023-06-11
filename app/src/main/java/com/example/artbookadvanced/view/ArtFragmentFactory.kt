package com.example.artbookadvanced.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbookadvanced.adapter.ArtAdapter
import com.example.artbookadvanced.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
class ArtFragmentFactory @Inject constructor(
    private val glide: RequestManager,
    private val artAdapter: ArtAdapter,
    private val imageAdapter: ImageAdapter
) :
    FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            ImageApiFragment::class.java.name->ImageApiFragment(imageAdapter)
            ArtFragment::class.java.name->ArtFragment(artAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            else -> return super.instantiate(classLoader, className)

        }

    }

}