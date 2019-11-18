package com.example.store.features.dashboard.data

import javax.inject.Inject
import com.example.store.core.api.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class StoreRemoteDataSource @Inject constructor(private val storeService: StoreService) {

    suspend fun getStoreInformation(): Result<StoreResponse>? {
        var result : Result<StoreResponse>? = null
        withContext(Dispatchers.IO){
            try {
            val asyncCall = storeService.getStoreInformation()
                when(asyncCall.code()){
                    200 -> {
                        asyncCall.body()?.let {
                            result = Result.Success(it)
                        }
                    }
                    else -> {
                        result = Result.Error("Connection error")
                    }
                }
            } catch (exception:Exception){
                result = Result.Error(exception.message?:" EXCEPTION ")
            }
        }
        return result
    }
}