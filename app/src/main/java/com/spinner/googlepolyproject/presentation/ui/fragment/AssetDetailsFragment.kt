package com.spinner.googlepolyproject.presentation.ui.fragment

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.spinner.googlepolyproject.R
import com.spinner.googlepolyproject.data.model.Asset
import com.spinner.googlepolyproject.utils.runDelayed
import com.spinner.googlepolyproject.utils.setImage
import kotlinx.android.synthetic.main.fragment_asset_details.*

class AssetDetailsFragment : BaseFragment() {

    private val args: AssetDetailsFragmentArgs by navArgs()

    override val layoutId = R.layout.fragment_asset_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postponeEnterTransition()

//        val transition = TransitionSet().apply {
//            addTransition(ChangeBounds())
//            addTransition(ChangeTransform())
//        }
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.move)
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun getArgs() {
        val asset = args.asset
        with(asset) {
            ivThumbnail.setImage(this.thumbnail.url, this@AssetDetailsFragment)
            ivThumbnail.transitionName = this.thumbnail.url
            tvDisplayName.text = this.displayName
            tvDisplayName.transitionName = this.displayName
            tvAuthorName.text = this.authorName
            tvAuthorName.transitionName = this.authorName
        }

        runDelayed({
            tvDescription.text = asset.description
        }, 70)
    }

    override fun setViews() {

    }

    override fun setClickListeners() {

    }

    override fun setObservers() {

    }

    companion object {
        private const val EXTRA_ASSET = "asset"

        fun newInstance(asset: Asset) =
            AssetDetailsFragment().apply {
                arguments = Bundle()
                arguments?.putParcelable(EXTRA_ASSET, asset)
            }
    }
}