package com.example.chibbisapp.features.review.data.api

import com.example.chibbisapp.features.review.data.model.ReviewRes
import retrofit2.http.GET

interface ReviewApi {
    @GET("/api/v1/reviews")
    suspend fun getReviews() : List<ReviewRes>
}