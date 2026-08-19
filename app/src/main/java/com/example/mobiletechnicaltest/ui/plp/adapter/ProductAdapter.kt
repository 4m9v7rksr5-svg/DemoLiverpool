package com.example.mobiletechnicaltest.ui.plp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletechnicaltest.databinding.ItemProductBinding
import com.example.mobiletechnicaltest.domain.model.Product

/**
 * // TODO Candidate: complete this adapter.
 * // TODO Candidate: - inflate item_product.xml with ItemProductBinding
 * // TODO Candidate: - bind product data (title, price, rating, category)
 * // TODO Candidate: - load the product image with Coil
 * // TODO Candidate: - invoke onProductSelected when a card is clicked
 */
class ProductAdapter(
    private val onProductSelected: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val products = mutableListOf<Product>()

    fun submitList(newProducts: List<Product>) {
        // TODO Candidate: replace the current list and notify the adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        // TODO Candidate: bind products[position] to holder.binding and set the click listener
    }

    override fun getItemCount(): Int = products.size

    class ProductViewHolder(
        val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
