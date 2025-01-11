package com.test.growMe.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.test.AgricMarketplace.R
import com.test.AgricMarketplace.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var openProducts: Button
    private lateinit var openCart: Button
    private lateinit var mainText: TextView

    private val categoriesViewModel: CategoriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                categoriesViewModel.products.collect { products ->
                    println(products)
                    mainText.text = products.toString()
                }
            }
        }

        openProducts.setOnClickListener { findNavController().navigate(R.id.action_categoriesFragment_to_productsFragment) }
        //openCart.setOnClickListener { findNavController().navigate(R.id.action_categoriesFragment_to_cartFragment) }
    }

    private fun initialization(){
        openProducts = binding.openProducts
        openCart = binding.openCart
        mainText = binding.text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}