package com.livtech.demo.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindAdapterUtils {
    @JvmStatic
    @BindingAdapter("app:hideShowView")
    fun hideShowView(view: View, visibility: Boolean) {
        if (visibility) view.visibility = View.VISIBLE else view.visibility = View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("app:goneVisible")
    fun goneVisible(view: View, visibility: Boolean) {
        if (visibility) view.visibility = View.VISIBLE else view.visibility = View.GONE
    }
    @JvmStatic
    @BindingAdapter("app:setBackground")
    fun setBackground(view: View, resId: Int) {
        view.setBackgroundResource(resId)
    }

    @JvmStatic
    @BindingAdapter("app:loadImage")
    fun loadImage(imageView: ImageView, imageURL: String?) {
        Glide.with(imageView.getContext())
            .load(imageURL)
            .into(imageView)
    }
}