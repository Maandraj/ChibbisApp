package com.example.chibbisapp.features.hits.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chibbisapp.features.hits.di.DaggerHitsComponent
import com.example.chibbisapp.features.hits.di.HitsComponent
import com.example.chibbisapp.features.hits.domain.HitsInteractor
import com.example.chibbisapp.features.hits.domain.model.Hit
import com.example.chibbisapp.features.restaurants.domain.model.ErrorData
import com.example.chibbisapp.utils.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
//TODO поднянуть контекст
class HitsVM(
    private val hitsInteractor: HitsInteractor,
) : ViewModel() {

    private val _hit = MutableLiveData<List<Hit>>()
    val hits = _hit.asLiveData()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.asLiveData()

    private val _error = MutableLiveData<ErrorData>()
    val error = _error.asLiveData()
    init {
        getHits()
    }
    fun getHits() = viewModelScope.launch {
        _loading.postValue(true)
        try {
            val hitsList = hitsInteractor.getHits()
            _hit.postValue(hitsList)
        } catch (ex: Exception) {
            _error.postValue(ErrorData(exception = ex, message = "Возникла ошибка получения хитов"))
        }
        _loading.postValue(false)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val hitsInteractor: Provider<HitsInteractor>,

    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == HitsVM::class.java)
            return HitsVM(
                hitsInteractor = hitsInteractor.get()) as T
        }
    }
}

class HitsComponentViewModel : ViewModel() {
    val hitsComponent: HitsComponent = DaggerHitsComponent.builder().build()
}