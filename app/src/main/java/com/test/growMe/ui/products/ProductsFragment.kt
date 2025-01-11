package com.test.growMe.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.test.AgricMarketplace.R
import com.test.AgricMarketplace.databinding.FragmentProductsBinding
import com.test.growMe.constants.Constants
import com.test.growMe.ui.categories.CategoriesViewModel
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var openCategories: Button
    private lateinit var openCart: Button
    private lateinit var mainText: TextView

    private lateinit var loadingText: TextView
    private lateinit var loadingProgress: ProgressBar

    private var selectedCategoryTag = ""

    private val productsViewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedCategoryTag = requireArguments().getString("categoryTag").toString()

        initialization()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                productsViewModel.updateStatus.collect { status ->
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
                productsViewModel.products.collect { products ->
                    when (products.isEmpty()){
                        false -> {
                            if (selectedCategoryTag.isNotBlank()) productsViewModel.setupProductsForCategory(selectedCategoryTag)
                        }
                        true -> mainText.text = "---"
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                productsViewModel.categoryProducts.collect { products ->
                    when (products.isEmpty()){
                        false -> {
                            println(products)
                            mainText.text = products.toString()

                            //
                            openCart.setOnClickListener { productsViewModel.addToCart(products[0]) }
                        }
                        true -> mainText.text = "---"
                    }
                }
            }
        }

        openCategories.setOnClickListener {
            //findNavController().navigate(R.id.action_categoriesFragment_to_productsFragment)

            findNavController().navigate(R.id.action_categoriesFragment_to_productsFragment, Bundle().apply {
                putString("categoryTag", "")
            })
        }

    }

    private fun initialization(){
        openCategories = binding.openCategories
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