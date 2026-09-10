package com.example.mobiletechnicaltest.ui.plp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiletechnicaltest.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.mobiletechnicaltest.ui.components.SortOption
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        getProducts()
    }

    fun sort(option: SortOption) {
        val currentState = _uiState.value
        if (currentState is ProductListUiState.Success) {
            val sortedList = when (option) {
                SortOption.PRICE_ASC -> currentState.products.sortedBy { it.price }
                SortOption.PRICE_DESC -> currentState.products.sortedByDescending { it.price }
                SortOption.RATING_ASC -> currentState.products.sortedBy { it.rating }
                SortOption.RATING_DESC -> currentState.products.sortedByDescending { it.rating }
            }
            _uiState.value = ProductListUiState.Success(sortedList)
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            _uiState.value = ProductListUiState.Loading
            try {
                val products = repository.getProducts()
                if (products.isEmpty()) {
                    _uiState.value = ProductListUiState.Empty
                } else {
                    _uiState.value = ProductListUiState.Success(products)
                }
            } catch (e: Exception) {
                _uiState.value = ProductListUiState.Error("Error al cargar los productos: ${e.message}")
            }
        }
    }
}
