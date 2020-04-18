package com.livtech.indihood.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.livtech.indihood.R
import com.livtech.indihood.databinding.FragmentDetailHeaderBinding
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

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentDetailHeaderBinding = DataBindingUtil.inflate<FragmentDetailHeaderBinding>(
            inflater,
            getLayout(),
            container,
            false
        )
        fragmentDetailHeaderBinding.headerViewModel = detailViewModel
        return fragmentDetailHeaderBinding.root
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
    }
}