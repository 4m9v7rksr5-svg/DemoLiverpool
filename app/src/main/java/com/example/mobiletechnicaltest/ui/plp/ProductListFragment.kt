package com.example.mobiletechnicaltest.ui.plp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mobiletechnicaltest.databinding.FragmentProductListBinding

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by viewModels()

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

        // TODO Candidate: build the PLP screen here.
        // TODO Candidate: - add a sort control (ComposeView hosting ProductSortCompose) to the layout
        // TODO Candidate: - add a RecyclerView (with a Product Card layout) to the layout
        // TODO Candidate: - collect ProductListViewModel.uiState and render loading/success/empty/error
        // TODO Candidate: - navigate to ProductDetailFragment when a product is selected
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
