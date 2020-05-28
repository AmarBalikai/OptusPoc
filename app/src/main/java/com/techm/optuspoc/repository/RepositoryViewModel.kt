package com.techm.optuspoc.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.techm.optuspoc.callback.ResponseCallback
import com.techm.optuspoc.model.ModelPhotosResponse
import com.techm.optuspoc.model.ModelUserInformation
import com.techm.optuspoc.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class for calling API
 * */
class RepositoryViewModel() {

    /**
     * This method for getting list of objects from the server
     * @param objCallback for get response to viewmodel
     */
        fun retrieveUserInformationData(objCallback: ResponseCallback<Any>) {
        val data: Call<ArrayList<ModelUserInformation>>? = ApiClient.build()?.getUserList()
        val enqueue = data?.enqueue(object : Callback<ArrayList<ModelUserInformation>> {
            override fun onResponse(
                call: Call<ArrayList<ModelUserInformation>>,
                response: Response<ArrayList<ModelUserInformation>>
            ) {
                if (response.isSuccessful) {
                    /**
                     * Send success response to viewModel using this callback
                     */
                    response.body()?.let { objCallback.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<ArrayList<ModelUserInformation>>, t: Throwable) {
                /**
                 * Send failure response to viewModel
                 */
                objCallback.onError(t.message)
            }
        })
    }

    fun retrievePhotos(objCallback: ResponseCallback<Any>,id:String) {
        val data: Call<ArrayList<ModelPhotosResponse>>? = ApiClient.build()?.getPhotosList(id)
        val enqueue = data?.enqueue(object : Callback<ArrayList<ModelPhotosResponse>> {
            override fun onResponse(
                call: Call<ArrayList<ModelPhotosResponse>>,
                response: Response<ArrayList<ModelPhotosResponse>>
            ) {
                if (response.isSuccessful) {
                    /**
                     * Send success response to viewModel using this callback
                     */
                    response.body()?.let { objCallback.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<ArrayList<ModelPhotosResponse>>, t: Throwable) {
                /**
                 * Send failure response to viewModel
                 */
                objCallback.onError(t.message)
            }
        })
    }
}