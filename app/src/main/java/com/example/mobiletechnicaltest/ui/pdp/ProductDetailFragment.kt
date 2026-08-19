package com.example.mobiletechnicaltest.ui.pdp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mobiletechnicaltest.databinding.FragmentProductDetailBinding

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

        binding.addToCartButton.setOnClickListener {
            // TODO Candidate: dummy "add to cart" action (no cart/persistence required)
        }

        binding.backToProductsButton.setOnClickListener {
            // TODO Candidate: navigate back to the PLP (e.g. findNavController().navigateUp())
        }

        // TODO Candidate: collect the ViewModel UI state and bind image/title/price/rating/description
        // TODO Candidate: handle loading and error states (progressBar / errorText)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
