package com.example.chibbisapp.features.hits.data.mapper

import com.example.chibbisapp.features.hits.data.model.HitsRes
import com.example.chibbisapp.features.hits.domain.model.Hit
import javax.inject.Inject

class HitsMapper @Inject constructor() {
    fun map(res: HitsRes) = Hit(
        productDescription = res.productDescriptionRes,
        productImage = res.productImageRes,
        productName = res.productNameRes,
        productPrice = res.productPriceRes,
        restaurantLogo = res.restaurantLogoRes,
        restaurantName = res.restaurantNameRes)
}