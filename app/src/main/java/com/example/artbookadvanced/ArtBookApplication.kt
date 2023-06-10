package com.example.artbookadvanced

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 1)Hilt'in giriş sınıfını oluşturuyoruz.
 *
 *  2)Bunu manifest'e bildiriyoruz.
 * */
@HiltAndroidApp
class ArtBookApplication:Application() {
}