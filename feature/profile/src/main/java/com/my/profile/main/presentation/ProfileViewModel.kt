package com.my.profile.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.core.presentation.BaseViewModel
import com.my.profile.main.domain.entity.ProfileFeature
import com.my.profile.main.domain.usecase.FetchFeaturesUseCase
import timber.log.Timber

class ProfileViewModel(
    fetchFeaturesUseCase: FetchFeaturesUseCase
) : BaseViewModel() {

    private val _features: MutableLiveData<List<ProfileFeature>> = MutableLiveData()
    val features: LiveData<List<ProfileFeature>> = _features

    init {
        fetchFeaturesUseCase()
            .subscribe {
                _features.value = it
            }.addToComposite()
    }

    fun onPageSelected(position: Int) {
        Timber.d("onPageSelected $position")
    }
}