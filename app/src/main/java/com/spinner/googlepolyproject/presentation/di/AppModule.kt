package com.spinner.googlepolyproject.presentation.di

import com.spinner.googlepolyproject.data.api.ApiFactory
import com.spinner.googlepolyproject.presentation.viewmodel.AssetsVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AssetsVM(createAssetsApiFactory()) }
}

private fun createAssetsApiFactory() = ApiFactory.createApiService()