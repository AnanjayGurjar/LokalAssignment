package com.ananjay.lokalassignment.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ananjay.lokalassignment.ProductViewModel
import com.ananjay.lokalassignment.api.ProductAPI
import com.ananjay.lokalassignment.databinding.FragmentMainBinding
import com.ananjay.lokalassignment.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel.getProducts()
        productViewModel.porductsLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success -> {
                    Log.d("TAG", "onCreateView: ${it.data}")
                }
                is NetworkResult.Error -> {
                    Log.e("TAG", "onCreateView: something went wrong", )
                }
                is NetworkResult.Loading -> {}
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
