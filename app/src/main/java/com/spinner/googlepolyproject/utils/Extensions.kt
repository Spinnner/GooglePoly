package com.spinner.googlepolyproject.utils

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.jetbrains.anko.toast

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, androidx.lifecycle.Observer {
        it?.let(observer)
    })
}

fun ImageView.setImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.setImage(url: String, fragment: Fragment) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            fragment.startPostponedEnterTransition()
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            fragment.startPostponedEnterTransition()
            return false
        }
    }

    Glide.with(this)
        .load(url)
        .listener(listener)
        .into(this)
}

inline fun runDelayed(noinline code: () -> Unit, delayMillis: Long) {
    Handler().postDelayed(code, delayMillis)
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