package com.livtech.demo.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.livtech.demo.core.repos.MessagesRepo

class MessageListViewModel : ViewModel() {
    private val _fetchMessage = MutableLiveData<Int>()
    val messages = _fetchMessage.switchMap {
        MessagesRepo.getMessagesByThreadId(it)
    }

    fun fetchMessages(threadId: Int) {
        _fetchMessage.value = threadId
    }
}