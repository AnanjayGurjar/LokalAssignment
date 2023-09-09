package com.ananjay.lokalassignment.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)