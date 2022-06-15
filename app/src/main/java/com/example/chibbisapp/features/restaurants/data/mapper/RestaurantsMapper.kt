package com.example.chibbisapp.features.restaurants.data.mapper

import com.example.chibbisapp.features.restaurants.data.model.RestaurantsRes
import com.example.chibbisapp.features.restaurants.domain.model.Restaurant
import javax.inject.Inject

class RestaurantsMapper @Inject constructor(
    private val specializationsMapper: SpecializationsMapper,
) {
    fun map(res: RestaurantsRes) = Restaurant(
        name = res.nameRes,
        positiveReviews = res.positiveReviewsRes,
        logo = res.logoRes,
        deliveryTime = res.deliveryTimeRes,
        minCost = res.minCostRes,
        specializations = res.specializationsRes.map { spec -> specializationsMapper.map(spec) }
    )
}