package com.techm.optuspoc.callback

import androidx.lifecycle.MutableLiveData
import com.techm.optuspoc.model.ModelUserInformation

/**
 * This interface for handling retrofit callbacks
 */
interface ResponseCallback {
    fun onSuccess(data: ArrayList<ModelUserInformation>)
    fun onError(error: String?)
}