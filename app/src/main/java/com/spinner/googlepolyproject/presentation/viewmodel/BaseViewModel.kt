package com.spinner.googlepolyproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

abstract class BaseViewModel : ViewModel() {
    private val subscriptions: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun Disposable.autoClear() {
        subscriptions += this
    }

    public override fun onCleared() {
        subscriptions.dispose()
    }
}