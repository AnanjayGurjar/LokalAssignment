package com.ananjay.lokalassignment.api

import com.ananjay.lokalassignment.models.Products
import retrofit2.Response
import retrofit2.http.GET


interface ProductAPI {
    @GET("/products")
    suspend fun getProducts(): Response<Products>
}