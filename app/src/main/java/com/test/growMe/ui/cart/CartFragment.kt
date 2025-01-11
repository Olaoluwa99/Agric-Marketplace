package com.test.growMe.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.test.AgricMarketplace.R
import com.test.AgricMarketplace.databinding.FragmentCartBinding
import com.test.AgricMarketplace.databinding.FragmentCategoriesBinding

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var openCategories: Button
    private lateinit var openProducts: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
    }

    private fun initialization(){
        openCategories = binding.openProducts
        openProducts = binding.openProducts
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}