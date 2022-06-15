package com.example.chibbisapp.features.review.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chibbisapp.features.restaurants.domain.model.ErrorData
import com.example.chibbisapp.features.review.di.DaggerReviewComponent
import com.example.chibbisapp.features.review.di.ReviewComponent
import com.example.chibbisapp.features.review.domain.ReviewInteractor
import com.example.chibbisapp.features.review.domain.model.Review
import com.example.chibbisapp.utils.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class ReviewVM(
    private val reviewInteractor: ReviewInteractor,
) : ViewModel() {
    private val _reviews = MutableLiveData<List<Review>>()
    val reviews = _reviews.asLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.asLiveData()

    private val _error = MutableLiveData<ErrorData>()
    val error = _error.asLiveData()

    init {
        getReviews()
    }

    fun getReviews() = viewModelScope.launch {
        _loading.postValue(true)
        try {
            val restaurants = reviewInteractor.getReviews()
            _reviews.postValue(restaurants)
        } catch (ex: Exception) {
            _error.postValue(ErrorData(exception = ex,
                message = "Возникла ошибка при получении отзывов"))
        }
        _loading.postValue(false)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val reviewInteractor: Provider<ReviewInteractor>,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ReviewVM::class.java)
            return ReviewVM(reviewInteractor.get()) as T
        }
    }
}

class ReviewComponentViewModel() : ViewModel() {
    val reviewComponent: ReviewComponent = DaggerReviewComponent.builder().build()
}