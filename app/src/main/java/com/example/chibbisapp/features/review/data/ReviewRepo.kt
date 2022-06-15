package com.example.chibbisapp.features.review.data

import com.example.chibbisapp.features.review.data.api.ReviewApi
import com.example.chibbisapp.features.review.data.mapper.ReviewMapper
import com.example.chibbisapp.features.review.domain.model.Review
import javax.inject.Inject

class ReviewRepo @Inject constructor(
    private val api: ReviewApi,
    private val mapper: ReviewMapper,
) {
    suspend fun getReviews(): List<Review> {
        val reviewRes = api.getReviews()
       return reviewRes.map { review -> mapper.map(review) }
    }
}