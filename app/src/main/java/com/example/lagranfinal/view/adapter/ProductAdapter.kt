package com.example.lagranfinal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lagranfinal.data.model.Product
import com.example.lagranfinal.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductAdapter(
    private var products: List<Product> = emptyList()
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var onClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.nameProduct.text = product.title
            binding.priceProduct.text = "Price: $${product.price}"
            Picasso.get().load(product.url).into(binding.imgProduct)

            val productId = product.id.toInt()
            binding.root.setOnClickListener {
                onClick?.invoke(productId)
            }
        }
    }

    fun setListProducts(productsResponse: List<Product>) {
        products = productsResponse
        notifyDataSetChanged()
    }

    /*
    fun connectApiToAdapter() {
        val call = ApiClient.getProduct.getProducts()
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    val productList = response.body()
                    if (productList != null) {
                        setListProducts(productList)
                    } else {
                        println("Error: Product list is null")
                    }
                } else {
                    println("Error in Response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }
    */
}
