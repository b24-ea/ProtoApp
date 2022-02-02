package com.aldanmaz.skyq.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aldanmaz.skyq.model.Proto
import com.aldanmaz.skyq.service.ProtoApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ListViewModel  : ViewModel(){

    private val protoApiService = ProtoApiService()
    private val disposable = CompositeDisposable()

    val proto = MutableLiveData<List<Proto>>()
    val protoError = MutableLiveData<Boolean>()
    val protoLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()

    }


    private fun getDataFromAPI() {
        protoLoading.value = true

        disposable.add(
            protoApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Proto>>(){
                    override fun onSuccess(t: List<Proto>) {
                        proto.value =  t
                        protoError.value = false
                        protoLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                     protoLoading.value = false
                        protoError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    override fun onCleared() {
       super.onCleared()

        disposable.clear()
    }


}







