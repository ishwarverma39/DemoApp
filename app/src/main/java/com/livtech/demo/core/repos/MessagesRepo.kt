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
import kotlinx.coroutines.Dispatchers

object MessagesRepo : BaseRepo() {
    private var messages: List<BranchMessage>? = null
    fun getMessage(): LiveData<Resource<List<BranchMessage>?>> {
        return makeApiCall(apiService(ApiServices::class.java).getMessagesAsync()).switchMap { resource ->
            liveData(Dispatchers.Default) {
                var res = resource
                if (res.status == NetworkStatus.SUCCESS) {
                    messages = res.data
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
}