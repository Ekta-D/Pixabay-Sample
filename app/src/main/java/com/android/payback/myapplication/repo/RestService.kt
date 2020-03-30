package com.android.payback.myapplication.repo

import com.android.payback.myapplication.model.ResponseModel
import retrofit2.http.*

interface RestService {

    @GET("?")
    suspend fun searchImage(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") image_type: String
    ): ResponseModel

}