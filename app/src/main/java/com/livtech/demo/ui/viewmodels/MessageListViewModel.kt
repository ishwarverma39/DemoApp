package com.livtech.demo.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.livtech.common.core.network.NetworkStatus
import com.livtech.demo.core.models.MessageRequest
import com.livtech.demo.core.repos.MessagesRepo

class MessageListViewModel : ViewModel() {
    private val _fetchMessage = MutableLiveData<Int>()
    val messages = _fetchMessage.switchMap {
        MessagesRepo.getMessagesByThreadId(it)
    }

    fun fetchMessages(threadId: Int) {
        _fetchMessage.value = threadId
    }

    private val _sendMessage = MutableLiveData<MessageRequest>()

    val sendMessageResponse = _sendMessage.switchMap { req ->
        MessagesRepo.sendMessage(req).switchMap { res ->
            if (res.status == NetworkStatus.SUCCESS) {
                fetchMessages(req.threadId)
            }
            liveData {
                emit(res)
            }
        }
    }

    fun sendMessage(threadId: Int, body: String) {
        if (body.isNotEmpty())
            _sendMessage.value = MessageRequest(threadId = threadId, body = body)
    }
}