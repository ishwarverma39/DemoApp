package com.livtech.indihood.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.livtech.indihood.R
import com.livtech.indihood.ui.viewmodels.ContentDetailViewModel

class DetailHeaderFragment : BaseFragment() {
    var detailViewModel: ContentDetailViewModel? = null
    override fun getLayout(): Int {
        return R.layout.fragment_detail_header
    }

    override fun initViewModels() {
        activity?.run {
            detailViewModel =
                ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(
                    ContentDetailViewModel::class.java
                )
        }
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
    }
}