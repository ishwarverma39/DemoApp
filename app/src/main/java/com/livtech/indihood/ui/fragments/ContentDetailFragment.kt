package com.livtech.indihood.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.livtech.indihood.R
import com.livtech.indihood.databinding.FragmentContentDetailBinding
import com.livtech.indihood.ui.viewmodels.ContentDetailViewModel
import kotlinx.android.synthetic.main.fragment_content_detail.*

class ContentDetailFragment : BaseFragment() {
    var detailViewModel: ContentDetailViewModel? = null

    override fun getLayout(): Int {
        return R.layout.fragment_content_detail
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
        val fragmentContentDetailBinding = DataBindingUtil.inflate<FragmentContentDetailBinding>(
            inflater,
            getLayout(),
            container,
            false
        )
        fragmentContentDetailBinding.detailViewModel = detailViewModel
        return fragmentContentDetailBinding.root
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        detailToolbar.setNavigationIcon(R.drawable.ic_action_back)
        detailToolbar.setNavigationOnClickListener { v -> onBackPress(v) }
        initSectionItemViews()
        initHeaderFragment()
        detailViewModel?.fetchContentDetail()
    }

    private fun initSectionItemViews() {
        val sectionListFragment = SectionListFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.sectionListContainer, sectionListFragment, "SECTION_LIST_FRAGMENT")
            .commit()
    }

    private fun initHeaderFragment() {
        val headerFragment = DetailHeaderFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.detailHeaderContainer, headerFragment, "HEADER_FRAGMENT").commit()
    }
}