package com.livtech.indihood.core.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.ErrorMessageParserImpl
import com.livtech.indihood.core.RecordParser
import com.livtech.indihood.core.apiservices.IndihoodApiServices
import com.livtech.indihood.core.models.ContentDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class ContentDetailRepo(scope: CoroutineScope, dispatcher: CoroutineDispatcher) :
    BaseRepo(scope, dispatcher) {

    fun getContentDetail(schemaUrl: String, recordUrl: String): LiveData<Resource<ContentDetail>> {
        return liveData(scope.coroutineContext + dispatcher)
        {
            var contentDetail: ContentDetail? = null
            emit(Resource.Loading(contentDetail))
            try {
                val schemaRes =
                    apiService(IndihoodApiServices::class.java).getSchema(schemaUrl).await()
                val recordRes =
                    apiService(IndihoodApiServices::class.java).getRecords(recordUrl).await()
                when (true) {
                    schemaRes.isSuccessful && recordRes.isSuccessful -> {
                        contentDetail = RecordParser(
                            schemaRes.body()!!,
                            recordRes.body()!!
                        ).parseDetail()
                        emit(
                            Resource.Success(
                                contentDetail
                            )
                        )
                    }
                    schemaRes.isSuccessful -> {
                        emit(
                            Resource.Failure(
                                ErrorMessageParserImpl().onApiCallFailure(recordRes),
                                contentDetail
                            )
                        )
                    }
                    else -> {
                        emit(
                            Resource.Failure(
                                ErrorMessageParserImpl().onApiCallFailure(schemaRes),
                                contentDetail
                            )
                        )
                    }
                }
            } catch (t: Throwable) {
                emit(Resource.Failure(ErrorMessageParserImpl().onNetworkFailure(t), contentDetail))
            }
        }
    }
}