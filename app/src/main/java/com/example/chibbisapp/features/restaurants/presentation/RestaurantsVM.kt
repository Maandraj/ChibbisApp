package com.example.chibbisapp.features.restaurants.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chibbisapp.features.restaurants.di.DaggerRestaurantsComponent
import com.example.chibbisapp.features.restaurants.di.RestaurantsComponent
import com.example.chibbisapp.features.restaurants.domain.RestaurantsInteractor
import com.example.chibbisapp.features.restaurants.domain.model.ErrorData
import com.example.chibbisapp.features.restaurants.domain.model.Restaurant
import com.example.chibbisapp.utils.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

//TODO поднянуть контекст
class RestaurantsVM(
    private val restaurantsInteractor: RestaurantsInteractor,
) : ViewModel() {


    private val _restaurant = MutableLiveData<List<Restaurant>>()
    val restaurants = _restaurant.asLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.asLiveData()

    private val _error = MutableLiveData<ErrorData>()
    val error = _error.asLiveData()

    init {
        getRestaurants()
    }

    fun getRestaurants() = viewModelScope.launch {
        _loading.postValue(true)
        try {
            val restaurants = restaurantsInteractor.getRestaurants()
            _restaurant.postValue(restaurants)
        } catch (ex: Exception) {
            _error.postValue(ErrorData(exception = ex,
                message = "Возникла ошибка при получении ресторанов"))
        }
        _loading.postValue(false)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val restaurantsInteractor: Provider<RestaurantsInteractor>,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == RestaurantsVM::class.java)
            return RestaurantsVM(restaurantsInteractor.get()) as T
        }
    }

}

class RestaurantComponentViewModel() : ViewModel() {
    val restaurantsComponent: RestaurantsComponent = DaggerRestaurantsComponent.builder() .build()
}

