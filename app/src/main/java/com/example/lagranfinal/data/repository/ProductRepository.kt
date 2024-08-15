package com.example.lagranfinal.data.repository

import com.example.lagranfinal.data.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>?
    suspend fun getProductById(id: Int): Product?
    suspend fun addProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
}