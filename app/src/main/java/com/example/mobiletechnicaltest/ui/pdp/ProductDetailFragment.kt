package com.example.mobiletechnicaltest.ui.pdp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.mobiletechnicaltest.databinding.FragmentProductDetailBinding
import com.example.mobiletechnicaltest.domain.model.Product
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductDetailViewModel by viewModels()

    // Navigation argument sent from ProductListFragment (see res/navigation/nav_graph.xml)
    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: ProductDetailState) {
        when (state) {
            is ProductDetailState.Loading -> {
                // Show loading indicator
            }
            is ProductDetailState.Success -> {
                showProductDetails(state.product)
            }
            is ProductDetailState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showProductDetails(product: Product) {
        // Update views with product data
        // For now, just update the placeholder text
        binding.helloText.text = "${product.title}\n\n${product.description}\n\nPrice: $${product.price}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
