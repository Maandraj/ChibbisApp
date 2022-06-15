package com.example.chibbisapp.features.restaurants.data.mapper

import com.example.chibbisapp.features.restaurants.data.model.SpecializationRes
import com.example.chibbisapp.features.restaurants.domain.model.Specialization
import javax.inject.Inject

class SpecializationsMapper @Inject constructor() {
    fun map(res: SpecializationRes) = Specialization(name = res.nameRes)
}