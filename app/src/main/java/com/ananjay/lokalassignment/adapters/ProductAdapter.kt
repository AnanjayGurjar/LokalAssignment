package com.ananjay.lokalassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ananjay.lokalassignment.databinding.ProductItemBinding
import com.ananjay.lokalassignment.models.Product
import com.bumptech.glide.Glide

class ProductAdapter(private val products: List<Product>, private val onClick: (Product) -> Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    inner class ProductViewHolder(private  val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            Glide.with(binding.root).load(product.thumbnail).into(binding.ivProduct)
            binding.tvTitle.text = product.title
            binding.tvBrand.text = product.brand
            binding.tvDiscount.text = "${product.discountPercentage}% off"
            binding.tvPrice.text = "$${product.price}"
            binding.root.setOnClickListener {
                onClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}