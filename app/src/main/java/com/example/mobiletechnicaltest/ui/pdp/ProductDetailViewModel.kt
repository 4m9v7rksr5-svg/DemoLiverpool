package com.example.mobiletechnicaltest.ui.pdp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiletechnicaltest.domain.model.Product
import com.example.mobiletechnicaltest.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ProductDetailState {
    object Loading : ProductDetailState()
    data class Success(val product: Product) : ProductDetailState()
    data class Error(val message: String) : ProductDetailState()
}

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ProductDetailState>(ProductDetailState.Loading)
    val state: StateFlow<ProductDetailState> = _state.asStateFlow()

    init {
        val productId = savedStateHandle.get<Int>("productId")
        if (productId != null) {
            loadProduct(productId)
        } else {
            _state.value = ProductDetailState.Error("Product ID not found")
        }
    }

    private fun loadProduct(id: Int) {
        viewModelScope.launch {
            _state.value = ProductDetailState.Loading
            val product = repository.getProduct(id)
            if (product != null) {
                _state.value = ProductDetailState.Success(product)
            } else {
                _state.value = ProductDetailState.Error("Failed to load product details")
            }
        }
    }
}
