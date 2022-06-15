package com.example.chibbisapp.features.review.data.mapper

import com.example.chibbisapp.features.review.data.model.ReviewRes
import com.example.chibbisapp.features.review.domain.model.Review
import javax.inject.Inject

class ReviewMapper @Inject constructor(

) {
    fun map(res:ReviewRes) = Review(
        dateAdded = res.dateAddedRes,
        isPositive = res.isPositiveRes,
        userFIO = res.userFIORes,
        restaurantName = res.restaurantNameRes,
        message = res.messageRes
    )
}