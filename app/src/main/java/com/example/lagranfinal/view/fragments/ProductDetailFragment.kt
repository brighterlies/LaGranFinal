package com.example.lagranfinal.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.lagranfinal.data.model.Product
import com.example.lagranfinal.databinding.FragmentProductDetailBinding
import com.example.lagranfinal.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val productDetailViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productId = arguments?.getInt("productId", -1) ?: -1
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        getProductData(productId)
        return binding.root
    }

    private fun getProductData(id: Int) {
        lifecycleScope.launch {
            if (id != -1) {
                val productUI = productDetailViewModel.getProductById(id)
                if (productUI != null) {
                    updateUI(productUI)
                } else {
                    println("Error: Product data is null")
                }
            } else {
                println("Error: Product ID is invalid")
            }
        }
    }

    private fun updateUI(product: Product) {
        binding.nameProduct.text = product.title
        binding.priceProduct.text = "Price: $${product.price}"
        binding.descriptionProduct.text = product.description
        binding.categoryProduct.text = product.category
        binding.ratingProduct.text = "Rating: ${product.rate}"
        binding.countProduct.text = "Count: ${product.count}"
        Picasso.get().load(product.url).into(binding.imgProduct)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
