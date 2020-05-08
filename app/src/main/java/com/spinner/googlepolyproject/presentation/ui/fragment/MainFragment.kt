package com.spinner.googlepolyproject.presentation.ui.fragment

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.spinner.googlepolyproject.R

class MainFragment : BaseFragment() {

    //private val navController by lazy { Navigation.findNavController(requireActivity(), R.id.nav_host_fragment) }

    override val layoutId = R.layout.fragment_main

    override fun getArgs() {
    }

    override fun setViews() {
        openFragment(AssetsFragment(), false)

//        requireActivity().onBackPressedDispatcher.addCallback(this) {
//            navController.navigateUp()
//        }
    }

    override fun setClickListeners() {
    }

    override fun setObservers() {
    }



//    private fun openStartFragment(fragment: Fragment) {
//        val transaction = childFragmentManager.beginTransaction()
//        transaction.add(R.id.contentMainFrame, fragment)
//        transaction.commitNow()
//    }

    fun openFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.contentMainFrame, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
            transaction.commit()
        } else transaction.commitNow()
    }
}