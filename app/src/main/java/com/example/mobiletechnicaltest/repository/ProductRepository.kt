package com.example.mobiletechnicaltest.repository

import com.example.mobiletechnicaltest.data.remote.FakeStoreApi
import com.example.mobiletechnicaltest.domain.model.Product

/**
 * Exposes product data to the ViewModels.
 *
 * // TODO Candidate: call [api], map the response DTOs into [Product],
 * // TODO Candidate: and handle network/parsing errors as you see fit.
 */
class ProductRepository(
    private val api: FakeStoreApi
) {

    suspend fun getProducts(): List<Product> {
        // TODO Candidate implementation
        return emptyList()
    }

    suspend fun getProduct(id: Int): Product? {
        // TODO Candidate implementation
        return null
    }
}
