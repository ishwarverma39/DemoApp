package com.livtech.demo.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livtech.demo.R
import com.livtech.demo.core.AppConstants
import com.livtech.demo.core.models.BranchMessage
import com.livtech.demo.databinding.FragmentThreadListBinding
import com.livtech.demo.ui.actitivities.MessagesActivity
import com.livtech.demo.ui.adapters.MessageThreadAdapter
import com.livtech.demo.ui.viewmodels.ThreadListViewModel

class ThreadListFragment : ViewDataBindingBaseFragment<FragmentThreadListBinding>() {
    private lateinit var adapter: MessageThreadAdapter
    private lateinit var threadListViewModel: ThreadListViewModel
    override fun getLayout(): Int {
        return R.layout.fragment_thread_list
    }

    override fun initViewModels() {
        threadListViewModel =
            ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(
                ThreadListViewModel::class.java
            )
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        adapter = MessageThreadAdapter(onItemClick = this::onItemClick)
        viewDataBinding.recycleView.adapter = adapter
        threadListViewModel.threads.observe(viewLifecycleOwner, Observer {
            viewDataBinding.resource = it
            adapter.submitList(it.data)
        })
    }

    private fun onItemClick(branchMessage: BranchMessage, pos: Int) {
        val intent = Intent(context, MessagesActivity::class.java).apply {
            putExtra(AppConstants.THREAD_ID_KEY, branchMessage.threadId)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        threadListViewModel.fetchMessages()
    }
}