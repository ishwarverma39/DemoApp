package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livtech.demo.R
import com.livtech.demo.core.models.BranchMessage
import com.livtech.demo.databinding.FragmentMessageThreadListBinding
import com.livtech.demo.ui.adapters.MessageThreadAdapter
import com.livtech.demo.ui.viewmodels.MessageListViewModel

class MessageThreadListFragment : ViewDataBindingBaseFragment<FragmentMessageThreadListBinding>() {
    private lateinit var adapter: MessageThreadAdapter
    private lateinit var messageListViewModel: MessageListViewModel
    override fun getLayout(): Int {
        return R.layout.fragment_message_thread_list
    }

    override fun initViewModels() {
        messageListViewModel =
            ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(
                MessageListViewModel::class.java
            )
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        adapter = MessageThreadAdapter(onItemClick = this::onItemClick)
        viewDataBinding.recycleView.adapter = adapter
        messageListViewModel.messages.observe(viewLifecycleOwner, Observer {
            viewDataBinding.resource = it
            adapter.submitList(it.data)
        })
        messageListViewModel.fetchMessages()
    }

    private fun onItemClick(branchMessage: BranchMessage, pos: Int) {

    }
}