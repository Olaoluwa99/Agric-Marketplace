package com.test.growMe.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.test.AgricMarketplace.R
import com.test.AgricMarketplace.databinding.FragmentCartBinding
import com.test.AgricMarketplace.databinding.FragmentCategoriesBinding
import com.test.growMe.ui.categories.CategoriesViewModel
import kotlinx.coroutines.launch

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var openCategories: Button
    private lateinit var openProducts: Button
    private lateinit var mainText: TextView

    private val cartViewModel: CartViewModel by activityViewModels()

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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartViewModel.products.collect { cartProducts ->
                    when (cartProducts.isEmpty()){
                        false -> {
                            println(cartProducts)
                            mainText.text = cartProducts.toString()
                        }
                        true -> mainText.text = "Loading..."
                    }
                }
            }
        }
    }

    private fun initialization(){
        openCategories = binding.openProducts
        openProducts = binding.openProducts
        mainText = binding.text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}