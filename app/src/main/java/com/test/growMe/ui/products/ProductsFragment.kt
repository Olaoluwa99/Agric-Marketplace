package com.test.growMe.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.test.AgricMarketplace.R
import com.test.AgricMarketplace.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var openCategories: Button
    private lateinit var openCart: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        openCategories.setOnClickListener { findNavController().navigate(R.id.action_productsFragment_to_categoriesFragment) }
        //openCart.setOnClickListener { findNavController().navigate(R.id.action_productsFragment_to_cartFragment) }
    }

    private fun initialization(){
        openCategories = binding.openCategories
        openCart = binding.openCart
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}