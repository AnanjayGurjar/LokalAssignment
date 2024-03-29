package com.ananjay.lokalassignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ananjay.lokalassignment.ProductViewModel
import com.ananjay.lokalassignment.R
import com.ananjay.lokalassignment.adapters.ProductAdapter
import com.ananjay.lokalassignment.api.ProductAPI
import com.ananjay.lokalassignment.databinding.FragmentMainBinding
import com.ananjay.lokalassignment.models.Product
import com.ananjay.lokalassignment.utils.InternetAvailibility
import com.ananjay.lokalassignment.utils.NetworkResult
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val productViewModel: ProductViewModel by viewModels<ProductViewModel>()

    @Inject
    lateinit var productAPI: ProductAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(InternetAvailibility.isOnline(requireContext())){
            fetchProducts()
            observeProducts()
        }else{
            showSnackBar()
        }
    }

    private fun showSnackBar(){
        binding.progressBar.isVisible = false
        val snackbar = Snackbar.make(
            binding.root,
            "No internet connection.",
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setActionTextColor(
            ContextCompat.getColor(
                requireContext(),
                androidx.constraintlayout.widget.R.color.material_grey_100
            )
        )
        snackbar.setAction("Try again") {
            if (InternetAvailibility.isOnline(context)) {
                fetchProducts()
                observeProducts()
            } else {
                showSnackBar()
            }
        }.show()
    }

    private fun fetchProducts(){
        productViewModel.getProducts()
    }

    private fun observeProducts(){
        productViewModel.porductsLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = true
            when(it){
                is NetworkResult.Success -> {
                    binding.progressBar.isVisible = false
                    if(it.data != null){
                        initRecyclerView(it.data!!.products);
                    }

                    Log.d("TAG", "onCreateView: ${it.data}")
                }
                is NetworkResult.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "onCreateView: something went wrong")
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }


    private fun initRecyclerView(products: List<Product>){
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = ProductAdapter(products,::onNoteItemClicked)
        binding.rvProducts.adapter = adapter
    }

    private fun onNoteItemClicked(product: Product){
        val bundle = Bundle()
        bundle.putString("product", Gson().toJson(product))
        findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
