package com.ananjay.lokalassignment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ananjay.lokalassignment.models.Product

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(products: List<Product>)

    @Query("Select * from product")
    fun getAllProducts(): LiveData<List<Product>>
}
