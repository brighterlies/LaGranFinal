package com.example.lagranfinal.data.network

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val id: String,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    @SerializedName("image")
    val url: String,
    val rating: Rating,
)

data class Rating(
    val rate: Double,
    val count: Int,
)
