package com.livtech.indihood.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindAdapterUtils {
    @BindingAdapter("app:hideShowView")
    fun hideShowView(view: View, visibility: Boolean) {
        if (visibility) view.visibility = View.VISIBLE else view.visibility = View.INVISIBLE
    }

    @BindingAdapter("app:goneVisible")
    fun goneVisible(view: View, visibility: Boolean) {
        if (visibility) view.visibility = View.VISIBLE else view.visibility = View.GONE
    }

    @BindingAdapter("app:setBackground")
    fun setBackground(view: View, resId: Int) {
        view.setBackgroundResource(resId)
    }

    @BindingAdapter("app:loadImage")
    fun loadImage(imageView: ImageView, imageURL: String?) {
        Glide.with(imageView.getContext())
            .load(imageURL)
            .into(imageView)
    }
}