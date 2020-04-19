package com.livtech.indihood.ui.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.livtech.common.core.models.Resource
import com.livtech.common.core.utils.DefaultDispatcherProvider
import com.livtech.indihood.core.AppConstants
import com.livtech.indihood.core.models.Address
import com.livtech.indihood.core.models.SectionItem
import com.livtech.indihood.core.repos.ContentDetailRepo

class ContentDetailViewModel : ViewModel() {
    val imageUrl = ObservableField("")
    val imageLabel = ObservableField("")
    val titleText = ObservableField("")
    val addressText = ObservableField("")
    val showLoading = ObservableField(false)
    val sectionItemsData = MutableLiveData<ArrayList<SectionItem>>()
    val addressData = MutableLiveData<Address>()
    private val contentDetailRepo =
        ContentDetailRepo(viewModelScope, DefaultDispatcherProvider().io())

    fun fetchContentDetail() {
        contentDetailRepo.getContentDetail(AppConstants.SCHEMA_URL, AppConstants.RECORDS_URL)
            .observeForever {
                when (it) {
                    is Resource.Loading -> {
                        showLoading.set(true)
                    }
                    is Resource.Success -> {
                        showLoading.set(false)
                        sectionItemsData.value = it.data?.sections
                        imageLabel.set(it.data?.imageLabel)
                        imageUrl.set(it.data?.imageUrl)
                        titleText.set(it.data?.title)
                        addressText.set(it.data?.address?.address)
                        addressData.value = it.data?.address
                    }
                    else -> {
                        showLoading.set(false)
                        //todo show error
                    }
                }
            }
    }
}