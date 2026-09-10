package com.example.mobiletechnicaltest.repository

import com.example.mobiletechnicaltest.domain.model.Product

/**
 * Exposes product data to the ViewModels.
 */
interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProduct(id: Int): Product?
}
