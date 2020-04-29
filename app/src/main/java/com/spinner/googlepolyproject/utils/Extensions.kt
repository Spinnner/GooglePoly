package com.spinner.googlepolyproject.utils

import android.app.Activity
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import org.jetbrains.anko.toast

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, androidx.lifecycle.Observer {
        it?.let(observer)
    })
}

fun Fragment.toast(message: String) {
    activity?.toast(message)
}

fun Fragment.toast(resId: Int) {
    activity?.toast(resId)
}

fun Activity.toastCenter(str: String) {
    toast(str).setGravity(Gravity.CENTER, 0, 0)
}

fun Activity.toastCenter(resId: Int) {
    toast(resId).setGravity(Gravity.CENTER, 0, 0)
}

fun Fragment.toastCenter(str: String) {
    activity?.toast(str)?.setGravity(Gravity.CENTER, 0, 0)
}

fun Fragment.toastCenter(resId: Int) {
    activity?.toast(resId)?.setGravity(Gravity.CENTER, 0, 0)
}

fun View.hide() {
    visibility = View.GONE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE