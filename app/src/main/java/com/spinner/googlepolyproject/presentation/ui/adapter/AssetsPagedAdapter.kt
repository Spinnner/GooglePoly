package com.spinner.googlepolyproject.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spinner.googlepolyproject.R
import com.spinner.googlepolyproject.data.model.Asset
import kotlinx.android.synthetic.main.rv_item_asset.view.*

class AssetsPagedAdapter :
    PagedListAdapter<Asset, AssetsPagedAdapter.AssetViewHolder>(ASSET_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_asset, parent, false)
        return AssetViewHolder(view)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val asset = getItem(position)
        asset?.let { holder.bind(it) }
    }

    class AssetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivThumbnail = view.ivThumbnail
        private val tvDisplayName = view.tvDisplayName
        private val tvAuthorName = view.tvAuthorName

        fun bind(asset: Asset) {
            Glide.with(ivThumbnail.context)
                .load(asset.thumbnail.url)
                .into(ivThumbnail)

            tvDisplayName.text = asset.displayName
            tvAuthorName.text = asset.authorName
        }
    }

    companion object {
        private val ASSET_COMPARATOR = object : DiffUtil.ItemCallback<Asset>() {

            override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean =
                oldItem.displayName == newItem.displayName

            override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean =
                newItem == oldItem
        }
    }
}