package com.example.chibbisapp.features.restaurants.domain

import com.example.chibbisapp.features.restaurants.data.RestaurantsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestaurantsInteractor @Inject constructor(
    private val restaurantsRepo: RestaurantsRepo
) {
    suspend fun getRestaurants() = withContext(Dispatchers.IO){
        restaurantsRepo.getRestaurants()
    }
}