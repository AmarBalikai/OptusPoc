package com.techm.optuspoc.network

import com.techm.optuspoc.model.ModelPhotosResponse
import com.techm.optuspoc.model.ModelUserInformation
import com.techm.optuspoc.utils.Constant
import retrofit2.Call
import retrofit2.http.GET

/**
 * Used this class for writing all API methods
 */
interface APIInterface {
    /**
     * This method is getting for list's of objects from server
     */
    @GET(Constant.userInfo)
    fun getUserList(): Call<ArrayList<ModelUserInformation>>

    /**
     * This method is getting for list's of photos from server
     * */
    @GET(Constant.getPhotos)
    fun getPhotosList(): Call<ArrayList<ModelPhotosResponse>>
}