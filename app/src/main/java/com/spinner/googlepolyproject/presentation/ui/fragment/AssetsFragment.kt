package com.spinner.googlepolyproject.presentation.ui.fragment

import com.spinner.googlepolyproject.R
import com.spinner.googlepolyproject.presentation.ui.adapter.AssetsPagedAdapter
import com.spinner.googlepolyproject.presentation.utils.Loading
import com.spinner.googlepolyproject.presentation.utils.NetworkError
import com.spinner.googlepolyproject.presentation.utils.Success
import com.spinner.googlepolyproject.presentation.viewmodel.AssetsVM
import com.spinner.googlepolyproject.utils.hide
import com.spinner.googlepolyproject.utils.nonNullObserve
import com.spinner.googlepolyproject.utils.show
import com.spinner.googlepolyproject.utils.toastCenter
import kotlinx.android.synthetic.main.fragment_assets.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AssetsFragment: BaseFragment() {

    private val vm: AssetsVM by viewModel()

    override val layoutId = R.layout.fragment_assets

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

        val adapter = AssetsPagedAdapter()
        vm.getAssets().nonNullObserve(this) {
            adapter.submitList(it)
        }

        rvAssets.adapter = adapter
    }

    private fun showProgress() {
        progressBar.show()
    }

    private fun hideProgress() {
        progressBar.hide()
    }
}