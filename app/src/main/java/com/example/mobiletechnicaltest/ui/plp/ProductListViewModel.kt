package com.example.mobiletechnicaltest.ui.plp

import androidx.lifecycle.ViewModel
import com.example.mobiletechnicaltest.data.remote.ApiClient
import com.example.mobiletechnicaltest.repository.ProductRepository

class ProductListViewModel : ViewModel() {

    private val repository = ProductRepository(ApiClient.api)

    // TODO Candidate:
    // - Expose UI state to the Fragment (e.g. StateFlow<ProductListUiState>)
    // - Load the products from the repository (viewModelScope + coroutines)
    // - Handle Loading / Success / Empty / Error
    // - Apply sorting (SortOption) to the currently loaded product list
}
