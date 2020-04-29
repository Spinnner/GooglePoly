package com.spinner.googlepolyproject.data.api

import com.spinner.googlepolyproject.data.model.AssetsResults
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AssetsApi {

    @GET("v1/assets/")
    fun getAssets(@Query("pageToken") pageToken: String): Single<AssetsResults>
}