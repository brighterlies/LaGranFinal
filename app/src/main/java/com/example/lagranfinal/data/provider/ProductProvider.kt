package com.example.lagranfinal.data.provider

import android.content.Context
import androidx.room.Room
import com.example.lagranfinal.data.database.ProductDatabase

class ProductProvider {
    private val PRODUCT_DATABASE_NAME = "product-db"

    fun providerRoom(context: Context): ProductDatabase {
        return Room.databaseBuilder(context, ProductDatabase::class.java, PRODUCT_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}