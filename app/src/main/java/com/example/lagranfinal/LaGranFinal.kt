package com.example.lagranfinal

import android.app.Application
import com.example.lagranfinal.data.database.ProductDatabase
import com.example.lagranfinal.data.provider.ProductProvider

class LaGranFinal : Application() {
    private val provider = ProductProvider()

    companion object {
        lateinit var database: ProductDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = provider.providerRoom(this)
    }
}