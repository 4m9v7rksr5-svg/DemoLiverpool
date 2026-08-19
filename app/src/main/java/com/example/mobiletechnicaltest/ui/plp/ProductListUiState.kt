package com.example.mobiletechnicaltest.ui.plp

import com.example.mobiletechnicaltest.domain.model.Product

/**
 * Initial UI state model for the PLP. Feel free to adjust this to whatever
 * shape best fits your ViewModel implementation.
 */
sealed interface ProductListUiState {
    data object Loading : ProductListUiState
    data class Success(val products: List<Product>) : ProductListUiState
    data object Empty : ProductListUiState
    data class Error(val message: String) : ProductListUiState
}
