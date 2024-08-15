package com.example.lagranfinal.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsInterface {
    @GET("products")
    fun getProducts(): Call<List<ProductResponse>>
}

object ApiClient {
    private const val BASE_URL = "https://fakestoreapi.com/"
    val getProduct: ProductsInterface
        get() {
            //gson created an object who is capable to save a Json
            val gson = GsonBuilder().setLenient().create()
            //created an access port to HTTP protocol
            val interceptor = HttpLoggingInterceptor()
            //Show all the information from the keys
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            //In this case, client is the Android Device
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(ProductsInterface::class.java)
        }
}