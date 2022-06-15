package com.example.chibbisapp.utils

import androidx.appcompat.widget.SearchView
import com.google.android.material.card.MaterialCardView

object SearchHelper {
    fun searchVisible(cardSearch: MaterialCardView, etSearch: SearchView, isVisible: Boolean) {
        if (isVisible) {
            cardSearch.layoutParams.width = android.widget.FrameLayout.LayoutParams.MATCH_PARENT
            etSearch.layoutParams.width = android.widget.FrameLayout.LayoutParams.MATCH_PARENT
        } else {
            cardSearch.layoutParams.width = 0
            etSearch.layoutParams.width = android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
        }
    }

}