package com.test.growMe.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.test.AgricMarketplace.R
import com.test.AgricMarketplace.databinding.FragmentCategoriesBinding
import com.test.growMe.constants.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var openProducts: Button
    private lateinit var openCart: Button
    private lateinit var mainText: TextView

    private lateinit var loadingText: TextView
    private lateinit var loadingProgress: ProgressBar

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
                categoriesViewModel.updateStatus.collect { status ->
                    when (status){
                        Constants.INACTIVE -> {
                            loadingProgress.visibility = View.GONE
                            loadingText.visibility = View.GONE
                        }
                        Constants.LOADING -> {
                            loadingProgress.visibility = View.VISIBLE
                            loadingText.visibility = View.GONE
                        }
                        Constants.FAILURE -> {
                            loadingProgress.visibility = View.GONE
                            loadingText.visibility = View.VISIBLE
                            loadingText.text = Constants.FAILURE
                        }
                        Constants.SUCCESS -> {
                            loadingProgress.visibility = View.GONE
                            loadingText.visibility = View.GONE
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                categoriesViewModel.products.collect { products ->
                    when (products.isEmpty()){
                        false -> {
                            println(products)
                            mainText.text = products.toString()

                            //
                            categoriesViewModel.assignProducts(products)
                            openCart.setOnClickListener { categoriesViewModel.addToCart(products[0]) }
                        }
                        true -> mainText.text = "---"
                    }
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
        loadingText = binding.progressText
        loadingProgress = binding.progressBar
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}