package com.chintansoni.android.tiaistiana.model.remote

import com.chintansoni.android.tiaistiana.model.remote.response.RandomUserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    fun getUsers(@Query("page") page: Int,
                 @Query("results") result: Int = 10,
                 @Query("lang") lang: String = "en"): Deferred<RandomUserResponse>
}