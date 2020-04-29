package com.spinner.googlepolyproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.spinner.googlepolyproject.data.api.AssetsApi
import com.spinner.googlepolyproject.data.model.Asset
import com.spinner.googlepolyproject.presentation.ui.adapter.AssetsDataSource
import com.spinner.googlepolyproject.presentation.ui.adapter.AssetsDataSourceFactory
import com.spinner.googlepolyproject.presentation.utils.NetworkState


class AssetsVM(repository: AssetsApi) : ViewModel() {

    private val assetsDataSourceFactory = AssetsDataSourceFactory(repository)
    private val assetPagedList: LiveData<PagedList<Asset>>
    //private lateinit var liveDataSource: LiveData<AssetsDataSource>

    init {
        assetPagedList = LivePagedListBuilder(assetsDataSourceFactory, AssetsDataSource.PAGE_SIZE)
            .build()
    }

    fun getAssets() = assetPagedList

    fun getNetworkState(): LiveData<NetworkState> = assetsDataSourceFactory.getAssetsDataSource().getNetworkState()

    override fun onCleared() {
        super.onCleared()
        assetsDataSourceFactory.getAssetsDataSource().clear()
    }
}