package com.spinner.googlepolyproject.presentation.ui.callback

import com.spinner.googlepolyproject.data.model.Asset
import com.spinner.googlepolyproject.presentation.ui.adapter.AssetsPagedAdapter

interface AssetsCallback {

    fun onAssetClicked(asset: Asset, iv: AssetsPagedAdapter.AssetViewHolder)
}