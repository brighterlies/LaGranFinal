package com.example.lagranfinal.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    @SerializedName("image")
    val url: String,
    val rate: Double,
    val count: Int,
)