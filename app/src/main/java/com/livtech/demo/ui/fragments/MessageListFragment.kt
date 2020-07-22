package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livtech.common.core.network.NetworkStatus
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
    private var messages: MutableList<BranchMessage>? = null

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
        initSendMessage()
        fetchMessage()
    }

    private fun initMessageList() {
        adapter = MessageListAdapter(onItemClick = this::onItemClick)
        recycleView.adapter = adapter
        messageListViewModel?.messages?.observe(viewLifecycleOwner, Observer {
            messages = it?.data?.toMutableList()
            adapter?.submitList(messages)
            scrollBottom()
        })
        recycleView.layoutManager
    }

    private fun fetchMessage() {
        messageListViewModel?.fetchMessages(threadId)
    }

    private fun initScroll() {
        recycleView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom
            ->
            if (bottom < oldBottom) scrollBottom()
        }
    }

    private fun scrollBottom() {
        messages?.size?.let {
            if (it > 0)
                recycleView.layoutManager?.smoothScrollToPosition(
                    recycleView,
                    null, it
                )
        }

    }

    private fun onItemClick(message: BranchMessage, position: Int) {
        //todo
    }

    private fun initSendMessage() {
        messageListViewModel?.sendMessageResponse?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                NetworkStatus.SUCCESS -> {
                    messageEdt.setText("")
                }
                NetworkStatus.FAILED, NetworkStatus.NO_INTERNET -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
            viewDataBinding.resource = it
        })
        sendButton.setOnClickListener {
            messageListViewModel?.sendMessage(threadId, messageEdt.text.toString())
        }
    }
}