package com.example.chibbisapp.features.hits.data

import com.example.chibbisapp.features.hits.data.api.HitsApi
import com.example.chibbisapp.features.hits.data.mapper.HitsMapper
import com.example.chibbisapp.features.hits.domain.model.Hit
import javax.inject.Inject

class HitsRepo @Inject constructor(
    private val api: HitsApi,
    private val hitsMapper: HitsMapper
) {
    suspend fun getHits() : List<Hit> {
        val hits = api.getHits()
        return hits.map { hit-> hitsMapper.map(hit) }
    }
}