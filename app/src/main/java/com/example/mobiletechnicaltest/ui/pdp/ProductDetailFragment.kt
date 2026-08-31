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

        // TODO Candidate: build the PDP screen here.
        // TODO Candidate: - add views for image, title, rating, price, description
        // TODO Candidate: - add ADD TO CART (dummy) and BACK TO PRODUCTS buttons
        // TODO Candidate: - collect ProductDetailViewModel state and render loading/success/error
        // TODO Candidate: - BACK TO PRODUCTS should navigate back to the PLP
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
