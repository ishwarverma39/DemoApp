package com.livtech.indihood.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.livtech.indihood.R
import com.livtech.indihood.core.models.SectionItem
import com.livtech.indihood.ui.adapters.SectionItemAdapter
import com.livtech.indihood.ui.viewmodels.ContentDetailViewModel
import kotlinx.android.synthetic.main.fragment_section_list.*

class SectionListFragment : BaseFragment() {
    private var detailViewModel: ContentDetailViewModel? = null

    override fun getLayout(): Int {
        return R.layout.fragment_section_list
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
        sectionRecyclerView.layoutManager = LinearLayoutManager(context)
        val items = ArrayList<SectionItem>()
        val adapter = SectionItemAdapter(items)
        sectionRecyclerView.adapter = adapter
        detailViewModel?.sectionItemsData?.observe(viewLifecycleOwner, Observer {
            items.apply {
                clear()
                addAll(it)
            }
            adapter.notifyDataSetChanged()
        })
    }
}