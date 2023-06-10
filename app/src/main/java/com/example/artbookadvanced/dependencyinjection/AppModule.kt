package com.example.artbookadvanced.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.artbookadvanced.api.RetrofitAPI
import com.example.artbookadvanced.roomdb.ArtDao
import com.example.artbookadvanced.roomdb.ArtDatabase
import com.example.artbookadvanced.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * 3)Modülü oluşturyoruz.
 *
 * */

//Hilt modülü olduğunu belirttik
@Module
//
@InstallIn(SingletonComponent::class)
object AppModule {
    //OLuşacak nesneye singleton tasarım deseni uygulanacağını belirttik
    @Singleton
    //İlgili metodun bir bağımlılığı sağlayacağını belirttik
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ArtDatabase::class.java, "ArtBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: ArtDatabase):ArtDao = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI(): RetrofitAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)
    }
}