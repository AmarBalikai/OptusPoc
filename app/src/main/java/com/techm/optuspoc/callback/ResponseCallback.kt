package com.techm.optuspoc.callback

import androidx.lifecycle.MutableLiveData
import com.techm.optuspoc.model.ModelUserInformation

/**
 * This interface for handling retrofit callbacks
 */
interface ResponseCallback<T> {
    /**
     * @param T is a generic parameter
     * */
    fun onSuccess(data: T)
    fun onError(error: String?)
}