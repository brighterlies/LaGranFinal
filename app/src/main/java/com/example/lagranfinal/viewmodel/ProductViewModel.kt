package com.example.lagranfinal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lagranfinal.data.model.Product
import com.example.lagranfinal.data.repository.ProductRepositoryImp

class ProductViewModel: ViewModel() {
    private val productRepositoryImp = ProductRepositoryImp()
    val product = MutableLiveData<Product>()

    suspend fun getProducts(): List<Product> {
        return productRepositoryImp.getProducts()
    }

    suspend fun getProductById(id: Int) {
        productRepositoryImp.getProductById(id)
    }

    suspend fun addProduct(product: Product) {
        productRepositoryImp.addProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productRepositoryImp.updateProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productRepositoryImp.deleteProduct(product)
    }
}
