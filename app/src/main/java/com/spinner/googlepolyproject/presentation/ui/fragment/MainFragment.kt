package com.spinner.googlepolyproject.presentation.ui.fragment

import com.spinner.googlepolyproject.R

class MainFragment: BaseFragment() {

    override val layoutId = R.layout.fragment_main

    override fun getArgs() {
    }

    override fun setViews() {
        openAssetsScreen()
    }

    override fun setClickListeners() {
    }

    override fun setObservers() {
    }

    private fun openAssetsScreen() {
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.contentMainFrame,
            AssetsFragment(), "assets_fragment")
        transaction.commitNow()
    }
}