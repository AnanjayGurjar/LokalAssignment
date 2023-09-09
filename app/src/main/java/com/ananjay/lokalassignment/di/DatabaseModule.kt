package com.ananjay.lokalassignment.di

import android.content.Context
import androidx.room.Room
import com.ananjay.lokalassignment.db.ProductDAO
import com.ananjay.lokalassignment.db.ProductDatabase
import com.ananjay.lokalassignment.models.Product
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): ProductDatabase{
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "products"
        ).build()
    }
    @Singleton
    @Provides
    fun providesDAO(productDatabase: ProductDatabase): ProductDAO{
        return productDatabase.productDao()
    }
}