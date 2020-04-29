package com.spinner.googlepolyproject.presentation.ui.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.spinner.googlepolyproject.data.api.AssetsApi
import com.spinner.googlepolyproject.data.model.Asset
import com.spinner.googlepolyproject.data.model.AssetsResults
import com.spinner.googlepolyproject.presentation.utils.*
import com.spinner.googlepolyproject.utils.schedulersIoToMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign


class AssetsDataSource(private val repository: AssetsApi) : PageKeyedDataSource<String, Asset>() {

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Asset>
    ) {
        networkState.postValue(Loading)
        compositeDisposable +=
            repository.getAssets("")
                .schedulersIoToMain()
                .subscribe({ assets -> onInitialItemsFetched(assets, callback) }, { e -> error(e) })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Asset>) {
        networkState.postValue(Loading)
        compositeDisposable +=
            repository.getAssets(params.key)
                .schedulersIoToMain()
                .subscribe({ assets -> onMoreItemsFetched(assets, callback) }, { e -> error(e) })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Asset>) {

    }

    private fun onInitialItemsFetched(
        data: AssetsResults,
        callback: LoadInitialCallback<String, Asset>
    ) {
        callback.onResult(data.assets, null, data.nextPageToken)
        networkState.postValue(Success)
    }

    private fun onMoreItemsFetched(
        data: AssetsResults,
        callback: LoadCallback<String, Asset>
    ) {
        callback.onResult(data.assets, data.nextPageToken)
        networkState.postValue(Success)
    }

    private fun error(e: Throwable) {
        networkState.postValue(NetworkError(e.message))
    }

    fun getNetworkState(): LiveData<NetworkState> = networkState

    fun clear() {
        compositeDisposable.clear()
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}