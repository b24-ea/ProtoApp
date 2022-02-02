package com.aldanmaz.skyq.service

import com.aldanmaz.skyq.model.Proto
import io.reactivex.Single
import retrofit2.http.GET

interface ProtoAPI {

    @GET("interview")
    fun getProto() : Single<List<Proto>>
}