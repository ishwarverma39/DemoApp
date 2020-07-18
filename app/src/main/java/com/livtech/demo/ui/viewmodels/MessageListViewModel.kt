package com.livtech.demo.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.livtech.demo.core.repos.MessagesRepo

class MessageListViewModel : ViewModel() {
    private val _fetchMessages = MutableLiveData<Boolean>()
    val messages = _fetchMessages.switchMap {
        MessagesRepo.getMessage()
    }

    fun fetchMessages() {
        _fetchMessages.value = true
    }
}