package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livtech.demo.R
import com.livtech.demo.core.AppConstants
import com.livtech.demo.core.models.BranchMessage
import com.livtech.demo.databinding.FragmentMessagesBinding
import com.livtech.demo.ui.adapters.MessageListAdapter
import com.livtech.demo.ui.viewmodels.MessageListViewModel
import kotlinx.android.synthetic.main.fragment_messages.*

class MessageListFragment : ViewDataBindingBaseFragment<FragmentMessagesBinding>() {
    private var messageListViewModel: MessageListViewModel? = null
    private var adapter: MessageListAdapter? = null
    private var threadId: Int = 0

    override fun initOnCreate(savedInstanceState: Bundle?) {
        super.initOnCreate(savedInstanceState)
        threadId = arguments?.getInt(AppConstants.THREAD_ID_KEY) ?: 0
    }

    override fun getLayout(): Int {
        return R.layout.fragment_messages
    }

    override fun initViewModels() {
        messageListViewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
            .get(MessageListViewModel::class.java)
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        initMessageList()
        initScroll()
        scrollBottom()
    }

    private fun initMessageList() {
        adapter = MessageListAdapter(onItemClick = this::onItemClick)
        recycleView.adapter = adapter
        messageListViewModel?.messages?.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it.data)
        })
        messageListViewModel?.fetchMessages(threadId)
        recycleView.layoutManager
    }

    private fun initScroll() {
        recycleView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom
            -> if (bottom < oldBottom) scrollBottom()
        }
    }

    private fun scrollBottom() {
        recycleView.layoutManager?.smoothScrollToPosition(
            recycleView,
            null,
            adapter?.itemCount ?: 0
        )
    }

    private fun onItemClick(message: BranchMessage, position: Int) {
        //todo
    }
}