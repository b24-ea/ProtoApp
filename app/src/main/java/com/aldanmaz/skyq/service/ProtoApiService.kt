package com.aldanmaz.skyq.service

import com.aldanmaz.skyq.model.Proto
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProtoApiService {
    //https://content-cache.watchcorridor.com/v6/interview

    private val BASE_URL = "https://content-cache.watchcorridor.com/v6/"

    private val END_PNT = "interview"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ProtoAPI::class.java)

    fun getData() : Single<List<Proto>> {
        return api.getProto()
    }


}