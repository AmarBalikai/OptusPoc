package com.techm.optuspoc.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class for creating Retrofit instance
 */
object ApiClient {
    /**
     * Application base URL
     * */
    private const val API_BASE_URL = " https://jsonplaceholder.typicode.com/"

    private var servicesApiInterface: ApiInterface? = null

    /**
     * This method is initialize for retrofit object
     * @return APIInterface instance
     */
    fun build(): ApiInterface? {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ApiInterface::class.java
        )
        return servicesApiInterface as ApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}