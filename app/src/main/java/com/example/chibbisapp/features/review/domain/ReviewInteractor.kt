package com.example.chibbisapp.features.review.domain

import com.example.chibbisapp.features.review.data.ReviewRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReviewInteractor @Inject constructor(
    private val reviewRepo: ReviewRepo
) {
    suspend fun getReviews() = withContext(Dispatchers.IO){
        reviewRepo.getReviews()
    }
}