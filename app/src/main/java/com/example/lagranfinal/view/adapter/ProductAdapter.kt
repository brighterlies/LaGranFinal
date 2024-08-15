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
}
