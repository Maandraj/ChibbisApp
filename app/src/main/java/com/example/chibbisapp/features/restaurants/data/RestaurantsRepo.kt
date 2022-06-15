package com.example.chibbisapp.features.restaurants.data

import com.example.chibbisapp.features.restaurants.data.api.RestaurantsApi
import com.example.chibbisapp.features.restaurants.data.mapper.RestaurantsMapper
import com.example.chibbisapp.features.restaurants.domain.model.Restaurant
import javax.inject.Inject

class RestaurantsRepo @Inject constructor(
    private val api: RestaurantsApi,
    private val mapper: RestaurantsMapper,
) {

    suspend fun getRestaurants(): List<Restaurant> {
        val restaurantsRes = api.getRestaurants()
        return restaurantsRes.map { rest -> mapper.map(rest) }
    }

}