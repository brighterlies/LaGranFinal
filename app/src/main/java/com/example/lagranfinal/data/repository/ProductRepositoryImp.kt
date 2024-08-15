package com.example.lagranfinal.data.repository

import com.example.lagranfinal.LaGranFinal
import com.example.lagranfinal.data.network.ProductResponse
import com.example.lagranfinal.data.model.Product
import com.example.lagranfinal.data.network.ApiClient
import com.example.lagranfinal.data.productEntityListToProductList
import com.example.lagranfinal.data.productEntityToProduct
import com.example.lagranfinal.data.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImp: ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            val productFromDatabase = LaGranFinal.database.productDao().getAll()
            if (productFromDatabase.isEmpty()){
                val productFromApi = getProductsApi()
                productFromApi?.forEach {
                    LaGranFinal.database.productDao().insertProduct(it.toEntity())
                }
                productFromApi ?: emptyList()
            }
            println(productFromDatabase)
            productEntityListToProductList(productFromDatabase)
        }
    }

    override suspend fun getProductById(id: Int): Product? {
        return withContext(Dispatchers.IO) {
            val productFromDatabase = LaGranFinal.database.productDao().getProductById(id)
            productFromDatabase?.let { productEntityToProduct(it) }
        }
    }

    override suspend fun addProduct(product: Product) {
        LaGranFinal.database.productDao().insertProduct(product.toEntity())
    }

    override suspend fun updateProduct(product: Product) {
        LaGranFinal.database.productDao().updateProduct(product.toEntity())
    }

    override suspend fun deleteProduct(product: Product) {
        LaGranFinal.database.productDao().deleteProduct(product.toEntity())
    }

    private suspend fun getProductsApi(): List<ProductResponse>? {
        return withContext(Dispatchers.IO) {
            val call = ApiClient.getProduct.getProducts()
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                println("Error in Response: ${response.code()}")
                null
            }
        }
    }
}