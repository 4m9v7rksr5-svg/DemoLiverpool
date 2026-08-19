package com.example.mobiletechnicaltest.data.remote

import com.example.mobiletechnicaltest.data.remote.dto.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {

    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): ProductResponse
}
