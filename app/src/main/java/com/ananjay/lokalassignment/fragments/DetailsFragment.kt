package com.ananjay.lokalassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ananjay.lokalassignment.R
import com.ananjay.lokalassignment.databinding.FragmentDetailsBinding
import com.ananjay.lokalassignment.databinding.FragmentMainBinding
import com.ananjay.lokalassignment.models.Product
import com.bumptech.glide.Glide
import com.google.gson.Gson


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jsonProduct = arguments?.getString("product")

        if(jsonProduct != null){
            val product = Gson().fromJson<Product>(jsonProduct, Product::class.java)
            Glide.with(view).load(product.thumbnail).into(binding.ivProduct)

            binding.tvBrand.text = product.brand
            binding.tvTitle.text = product.title
            binding.tvDescription.text = product.description
            binding.tvPrice.text = getString(R.string.price, product.price)
            binding.tvDiscount.text = getString(R.string.discount, product.discountPercentage)
            binding.tvStock.text = getString(R.string.stock, product.stock)
        }else{
            //should not happen

            binding.tvTitle.text = ""
            binding.tvDescription.text = ""
            binding.tvPrice.text = ""
            binding.tvDiscount.text = ""
            binding.tvStock.text = ""
            Toast.makeText(requireContext(), "No product found", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

    }

}