package com.example.artbookadvanced.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    val name:String,
    val artistName:String,
    val year:Int,
    val imgUrl:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null
)
