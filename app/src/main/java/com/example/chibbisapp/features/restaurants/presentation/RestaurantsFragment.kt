package com.example.chibbisapp.features.restaurants.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.chibbisapp.R
import com.example.chibbisapp.databinding.FragmnentRestaurantsBinding
import com.example.chibbisapp.features.restaurants.domain.model.Restaurant
import com.example.chibbisapp.features.restaurants.presentation.adapter.RestaurantsAdapter
import com.example.chibbisapp.utils.SearchHelper.searchVisible
import dagger.Lazy
import javax.inject.Inject

class RestaurantsFragment : Fragment(R.layout.fragmnent_restaurants) {

    @Inject
    internal lateinit var restaurantsVMFactory: Lazy<RestaurantsVM.Factory>

    private val viewModel: RestaurantsVM by viewModels {
        restaurantsVMFactory.get()
    }

    private val binding: FragmnentRestaurantsBinding by viewBinding(FragmnentRestaurantsBinding::bind)

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<RestaurantComponentViewModel>() .restaurantsComponent.inject(this)
        super.onAttach(context)
    }

    private var restaurantsAdapter: RestaurantsAdapter? = null
    private val restaurantData = mutableListOf<Restaurant>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
        setSearchView()
    }

    var querySearch: String? = null
    private fun setSearchView() {
        with(binding.includeSearch) {
            etSearch.setOnSearchClickListener {
                searchVisible(cardSearch = cardSearch,etSearch = etSearch,isVisible = true)
            }
            etSearch.setOnCloseListener {
                searchVisible(cardSearch = cardSearch,etSearch = etSearch,isVisible = false)
                false
            }
            etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty() && querySearch != null) {
                        querySearch = null
                        viewModel.getRestaurants()
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    etSearch.onActionViewCollapsed()
                    searchVisible(cardSearch = cardSearch,etSearch = etSearch,isVisible = false)
                    if (query.isNotEmpty() && querySearch != query) {
                        querySearch = query.ifEmpty { null }
                        etSearch.setQuery(querySearch, false)
                        viewModel.getRestaurants()
                    }
                    return true
                }
            })
        }

    }

    private fun setUpRecyclerViewClickListener(){
        restaurantsAdapter?.restaurantOnClick = { restaurant, position->
            Toast.makeText(requireContext(),"Ресторан ${restaurant.name} позиция $position", Toast.LENGTH_SHORT).show()
        }
    }
    //TODO Сюда бы DiffUtils, но увы времени мало
    @SuppressLint("NotifyDataSetChanged")
    private fun setObservables() {
        viewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            var filterRestaurants = restaurants

            querySearch?.let {
                val _query = it.lowercase()
                filterRestaurants = restaurants.filter { restaurant ->
                    restaurant.name.lowercase().indexOf(_query) != -1 ||
                            restaurant.specializations.any { spec -> spec.name.lowercase() == _query }
                }
            }
            restaurantData.clear()
            restaurantData.addAll(filterRestaurants)
            binding.includeNotFound.root.visibility =
                if (restaurantData.isNotEmpty()) View.GONE else View.VISIBLE
            if (restaurantsAdapter == null) {
                restaurantsAdapter = RestaurantsAdapter(restaurantData)
                binding.rvRestaurants.layoutManager = LinearLayoutManager(requireContext())
                binding.rvRestaurants.adapter = restaurantsAdapter
                setUpRecyclerViewClickListener()
            } else {
                restaurantsAdapter?.notifyDataSetChanged()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.includeLoading.root.visibility = if (loading) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Log.e(TAG, error.exception.message.toString())
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        restaurantsAdapter = null
    }
}

private const val TAG = "RestaurantsFragment"