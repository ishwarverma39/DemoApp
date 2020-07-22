package com.livtech.demo.core.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.AE_404
import com.livtech.common.core.network.ApiError
import com.livtech.common.core.network.NetworkStatus
import com.livtech.demo.core.ApiServices
import com.livtech.demo.core.models.BranchMessage
import com.livtech.demo.core.models.MessageRequest
import kotlinx.coroutines.Dispatchers

object MessagesRepo : BaseRepo() {
    private var messages: MutableList<BranchMessage>? = null
    fun getMessage(): LiveData<Resource<List<BranchMessage>?>> {
        return makeApiCall(apiService(ApiServices::class.java).getMessagesAsync()).switchMap { resource ->
            liveData(Dispatchers.Default) {
                var res = resource
                if (res.status == NetworkStatus.SUCCESS) {
                    messages = res.data?.toMutableList()
                }
                val threadList = mutableListOf<BranchMessage>()
                messages?.groupBy { it.threadId }?.forEach {
                    threadList.add(it.value.sortedByDescending { message -> message.timestamp }[0])
                }
                if (threadList.isNotEmpty()) res =
                    Resource.Success(threadList.sortedByDescending { it.timestamp })
                emit(res)
            }
        }
    }

    fun getMessagesByThreadId(threadId: Int): LiveData<Resource<List<BranchMessage>>> {
        return liveData(Dispatchers.Default) {
            emit(Resource.Loading(null))
            val localMessages =
                messages?.groupBy { it.threadId }?.get(threadId)?.sortedBy { it.timestamp }
            if (localMessages.isNullOrEmpty())
                emit(Resource.Failure(ApiError(AE_404, "Not messages found")))
            else emit(Resource.Success(localMessages))
        }
    }

    fun sendMessage(messageRequest: MessageRequest): LiveData<Resource<BranchMessage?>> {
        val body = HashMap<String, Any>().apply {
            put("thread_id", messageRequest.threadId)
            put("body", messageRequest.body)
        }
        return makeApiCall(apiService(ApiServices::class.java).sendMessageAync(body)).switchMap { resource ->
            liveData(Dispatchers.Default) {
                if (resource.status == NetworkStatus.SUCCESS && resource.data != null) {
                    messages?.add(resource.data!!)
                }
                emit(resource)
            }
        }
    }
}