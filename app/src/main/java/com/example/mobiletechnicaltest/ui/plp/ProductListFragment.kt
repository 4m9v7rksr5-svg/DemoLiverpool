package com.example.mobiletechnicaltest.ui.plp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiletechnicaltest.databinding.FragmentProductListBinding
import com.example.mobiletechnicaltest.ui.components.ProductSortCompose
import com.example.mobiletechnicaltest.ui.plp.adapter.ProductAdapter

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by viewModels()

    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSortCompose()

        // TODO Candidate: collect the ViewModel UI state (StateFlow) and update
        // TODO Candidate: recyclerView / progressBar / errorText / emptyText visibility
        // TODO Candidate: trigger the initial product load
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter(
            onProductSelected = { product ->
                // TODO Candidate: navigate to ProductDetailFragment passing product.id
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupSortCompose() {
        binding.sortComposeView.setContent {
            MaterialTheme {
                ProductSortCompose(
                    onSortSelected = { sortOption ->
                        // TODO Candidate: apply sortOption to the current product list
                    }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
