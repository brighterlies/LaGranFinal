package com.example.lagranfinal.data

import com.example.lagranfinal.data.database.ProductEntity
import com.example.lagranfinal.data.network.ProductResponse
import com.example.lagranfinal.data.model.Product

fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    url = url,
    rate = rate,
    count = count
)

fun ProductResponse.toEntity(): ProductEntity = ProductEntity(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    url = url,
    rate = rating.rate,
    count = rating.count
)

fun productEntityToProduct(productEntity: ProductEntity): Product = Product(
    id = productEntity.id,
    title = productEntity.title,
    price = productEntity.price,
    category = productEntity.category,
    description = productEntity.description,
    url = productEntity.url,
    rate = productEntity.rate,
    count = productEntity.count
)

fun productEntityListToProductList(list: List<ProductEntity>): List<Product> = list.map(::productEntityToProduct)