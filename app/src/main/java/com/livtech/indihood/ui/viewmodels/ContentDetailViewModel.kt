package com.livtech.indihood.ui.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livtech.indihood.core.models.SectionItem

class ContentDetailViewModel : ViewModel() {
    val imageUrl = ObservableField("")
    val imageLabel = ObservableField("")
    val titleText = ObservableField("")
    val addressText = ObservableField("")
    val showLoading = ObservableField(false)

    val sectionItemsData = MutableLiveData<ArrayList<SectionItem>>()
}