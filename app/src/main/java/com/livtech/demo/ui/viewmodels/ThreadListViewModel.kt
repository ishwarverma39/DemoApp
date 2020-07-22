package com.livtech.demo.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.livtech.demo.core.repos.MessagesRepo

class ThreadListViewModel : ViewModel() {
    private val _fetchMessageThreads = MutableLiveData<Boolean>()
    val threads = _fetchMessageThreads.switchMap {
        MessagesRepo.getMessage()
    }

    fun fetchMessages() {
        _fetchMessageThreads.value = true
    }
}