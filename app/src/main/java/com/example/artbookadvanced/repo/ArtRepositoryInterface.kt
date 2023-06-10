package com.example.artbookadvanced.repo

import androidx.lifecycle.LiveData
import com.example.artbookadvanced.model.ImageResponse
import com.example.artbookadvanced.roomdb.Art
import com.example.artbookadvanced.util.Resource

//Test için fake repo oluşturmak gerektiğinde kolaylık sağlaması için bu interface i oluşturduk
interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>

}