package com.ananjay.lokalassignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ananjay.lokalassignment.models.Product


@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDAO
}