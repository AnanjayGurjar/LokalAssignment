package com.ananjay.lokalassignment.api

import androidx.lifecycle.LiveData
import com.ananjay.lokalassignment.models.Product
import retrofit2.Response
import retrofit2.http.GET


interface ProductAPI {
    @GET("/products")
    suspend fun getProducts(): Response<List<Product>>
}