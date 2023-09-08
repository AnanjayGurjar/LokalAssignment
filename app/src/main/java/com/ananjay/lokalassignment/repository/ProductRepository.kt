package com.ananjay.lokalassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ananjay.lokalassignment.api.ProductAPI
import com.ananjay.lokalassignment.models.Products
import com.ananjay.lokalassignment.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productAPI: ProductAPI){

    private val _productsLiveData = MutableLiveData<NetworkResult<Products>>()
    val productsLiveData : LiveData<NetworkResult<Products>>
    get() = _productsLiveData

    suspend fun getProducts(){
        _productsLiveData.postValue(NetworkResult.Loading())
        val response = productAPI.getProducts()
        if(response.isSuccessful && response.body() != null){
            _productsLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else if(response.errorBody() != null){
            _productsLiveData.postValue(NetworkResult.Error(response.errorBody().toString()))
        }else{
            _productsLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}