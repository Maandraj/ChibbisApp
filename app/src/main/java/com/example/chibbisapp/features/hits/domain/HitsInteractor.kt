package com.example.chibbisapp.features.hits.domain

import com.example.chibbisapp.features.hits.data.HitsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HitsInteractor @Inject constructor(
    private val hitsRepo: HitsRepo
) {
    suspend fun getHits() = withContext(Dispatchers.IO){
        hitsRepo.getHits()
    }
}