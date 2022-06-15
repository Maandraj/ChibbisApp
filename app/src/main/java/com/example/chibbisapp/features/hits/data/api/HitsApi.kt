package com.example.chibbisapp.features.hits.data.api

import com.example.chibbisapp.features.hits.data.model.HitsRes
import retrofit2.http.GET


interface HitsApi {
    @GET("/api/v1/hits")
    suspend fun getHits() : List<HitsRes>
}