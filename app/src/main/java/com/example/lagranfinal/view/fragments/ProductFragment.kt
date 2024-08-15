package com.example.lagranfinal.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lagranfinal.R
import com.example.lagranfinal.databinding.FragmentProductBinding
import com.example.lagranfinal.viewmodel.ProductViewModel
import com.example.lagranfinal.view.adapter.ProductAdapter
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var recyclerView: RecyclerView
    private val productAdapter: ProductAdapter = ProductAdapter()
    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        getProducts()
        startRecyclerView()
        setupProductDetailNavigation()
        return binding.root
    }

    private fun startRecyclerView() {
        recyclerView = binding.rvProduct
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = productAdapter
    }

    private fun setupProductDetailNavigation() {
        productAdapter.onClick = { itemId ->
            val bundle = bundleOf("productId" to itemId)
            val productDetailFragment = ProductDetailFragment()
            productDetailFragment.arguments = bundle
            setCurrentFragment(productDetailFragment)
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }

    private fun getProducts() {
        lifecycleScope.launch {
            val products = productViewModel.getProducts()
            productAdapter.setListProducts(products)
        }
    }
}