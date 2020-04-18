package com.livtech.indihood.ui.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ContentDetailViewModel : ViewModel() {
    val imageUrl = ObservableField("")
    val imageLabel = ObservableField("")
    val titleText = ObservableField("")
    val addressText = ObservableField("")
}