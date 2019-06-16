package com.carousell.newsapp.utils.ext

import androidx.lifecycle.MutableLiveData
import com.carousell.newsapp.utils.network.Resource
import com.carousell.newsapp.utils.network.ResourceState

/**
 * Created by SangiliPandian C on 16-06-2019.
 */
fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) =
    postValue(Resource(ResourceState.SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(Resource(ResourceState.LOADING, value?.data))

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(Resource(ResourceState.ERROR, value?.data, message))