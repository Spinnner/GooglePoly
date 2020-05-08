package com.spinner.googlepolyproject.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.spinner.googlepolyproject.R
import com.spinner.googlepolyproject.data.model.Asset
import com.spinner.googlepolyproject.presentation.ui.adapter.AssetsPagedAdapter
import com.spinner.googlepolyproject.presentation.ui.callback.AssetsCallback
import com.spinner.googlepolyproject.presentation.utils.Loading
import com.spinner.googlepolyproject.presentation.utils.NetworkError
import com.spinner.googlepolyproject.presentation.utils.Success
import com.spinner.googlepolyproject.presentation.viewmodel.AssetsVM
import com.spinner.googlepolyproject.utils.hide
import com.spinner.googlepolyproject.utils.nonNullObserve
import com.spinner.googlepolyproject.utils.show
import com.spinner.googlepolyproject.utils.toastCenter
import kotlinx.android.synthetic.main.fragment_assets.*
import kotlinx.android.synthetic.main.rv_item_asset.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AssetsFragment: BaseFragment(), AssetsCallback {

    private val vm: AssetsVM by viewModel()
    private val adapter_ = lazy { AssetsPagedAdapter(this) }

    private var view1: View? = null

    override val layoutId = R.layout.fragment_assets

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(view1 == null) view1 = inflater.inflate(layoutId, container, false)
        return view1
    }

    override fun getArgs() {
    }

    override fun setViews() {

    }

    override fun setClickListeners() {
    }

    override fun setObservers() {
        vm.getNetworkState().nonNullObserve(this) {
            hideProgress()
            when(it) {
                is Loading -> showProgress()
                is Success -> hideProgress()
                is NetworkError -> it.message?.let { toastCenter(it) }
            }
        }

        vm.getAssets().nonNullObserve(this) {
            adapter_.value.submitList(it)
        }

        if(adapter_.isInitialized().not()) {
            rvAssets.apply {
                adapter = adapter_.value
                postponeEnterTransition()
                viewTreeObserver
                    .addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
            }
        }
    }

    private fun showProgress() {
        progressBar.show()
    }

    private fun hideProgress() {
        progressBar.hide()
    }

    override fun onAssetClicked(asset: Asset, viewHolder: AssetsPagedAdapter.AssetViewHolder) {
        //val fragment = AssetDetailsFragment.newInstance(asset)
        //(parentFragment as MainFragment).openFragment(fragment)


//        val bundle = bundleOf("asset" to asset)
//        findNavController().navigate(R.id.assetDetailsFragment, bundle)

        val view = viewHolder.itemView
        val extras = FragmentNavigatorExtras(view.ivThumbnail to asset.thumbnail.url, view.tvDisplayName to asset.displayName, view.tvAuthorName to asset.authorName)
        val action = AssetsFragmentDirections.actionAssetsFragmentToAssetDetailsFragment(asset)
        findNavController().navigate(action, extras)
    }
}