package com.ananjay.lokalassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ananjay.lokalassignment.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel(){
    val porductsLiveData get() = productRepository.productsLiveData
    fun getProducts(){
        viewModelScope.launch {
            productRepository.getProducts()
        }
    }
}