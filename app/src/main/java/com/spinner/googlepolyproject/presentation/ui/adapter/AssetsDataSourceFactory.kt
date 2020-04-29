package com.spinner.googlepolyproject.presentation.ui.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.spinner.googlepolyproject.data.api.AssetsApi
import com.spinner.googlepolyproject.data.model.Asset

class AssetsDataSourceFactory(repository: AssetsApi): DataSource.Factory<String, Asset>() {

    private val assetsLiveDataSource = MutableLiveData<AssetsDataSource>()
    private val assetsDataSource = AssetsDataSource(repository)

    override fun create(): DataSource<String, Asset> {
        assetsLiveDataSource.postValue(assetsDataSource)

        return assetsDataSource
    }

    fun getAssetsDataSource() = assetsDataSource
    fun getAssetsLiveDataSource() = assetsLiveDataSource
}