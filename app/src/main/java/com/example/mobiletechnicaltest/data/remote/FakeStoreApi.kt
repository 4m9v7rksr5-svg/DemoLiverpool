package com.example.mobiletechnicaltest.data.remote

import android.util.Log
import com.example.mobiletechnicaltest.data.remote.dto.ProductResponse
import com.example.mobiletechnicaltest.domain.model.Product
import com.example.mobiletechnicaltest.repository.ProductRepository
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

/**
 * Retrofit service definition for the Fake Store API.
 */
interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductResponse
}

/**
 * Implementation of [ProductRepository] that fetches data from [ProductApiService].
 */
class FakeStoreApi @Inject constructor(
    private val apis: ProductApiService
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return try {
            val response = apis.getProducts()
            Log.d("FakeStoreApi", "getProducts: Success, items=${response.size}")
            response.map { it.toDomain() }
        } catch (e: Exception) {
            Log.e("FakeStoreApi", "getProducts: Error fetching products: ${e.message}", e)
            emptyList()
        }
    }

    override suspend fun getProduct(id: Int): Product? {
        return try {
            val response = apis.getProduct(id)
            Log.d("FakeStoreApi", "getProduct: Success, id=$id")
            response.toDomain()
        } catch (e: Exception) {
            Log.e("FakeStoreApi", "getProduct: Error fetching product $id: ${e.message}", e)
            null
        }
    }
}

/**
 * Extension to map [ProductResponse] DTO to domain [Product] model.
 */
fun ProductResponse.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        imageUrl = image,
        rating = rating.rate,
        ratingCount = rating.count
    )
}
