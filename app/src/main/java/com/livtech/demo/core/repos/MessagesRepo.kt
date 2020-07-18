package com.livtech.demo.core.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.NetworkStatus
import com.livtech.demo.core.ApiServices
import com.livtech.demo.core.models.BranchMessage

object MessagesRepo : BaseRepo() {
    private var messages: List<BranchMessage>? = null
    fun getMessage(): LiveData<Resource<List<BranchMessage>?>> {
        return makeApiCall(apiService(ApiServices::class.java).getMessagesAsync()).switchMap {
            var res = it
            if (res.status == NetworkStatus.SUCCESS) {
                messages = it.data
                val threadList = mutableListOf<BranchMessage>()
                res.data?.groupBy { it.threadId }?.forEach {
                    threadList.add(it.value.sortedByDescending { message -> message.timestamp }[0])
                }
                res = Resource.Success(threadList)
            }
            liveData { emit(res) }
        }
    }
}