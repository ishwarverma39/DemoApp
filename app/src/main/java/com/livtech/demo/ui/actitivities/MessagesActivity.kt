package com.livtech.demo.ui.actitivities

import android.os.Bundle
import com.livtech.demo.core.AppConstants
import com.livtech.demo.ui.fragments.MessageListFragment

class MessagesActivity : AppBarActivity() {
    private var threadId: Int = 0

    override fun initOnCreate(savedInstanceState: Bundle?) {
        threadId = intent.getIntExtra(AppConstants.THREAD_ID_KEY, 0)
        super.initOnCreate(savedInstanceState)
    }

    override fun getTitleText(): String {
        return threadId.toString()
    }

    override fun initViews() {
        super.initViews()
        showBackButton()
        val fragment = MessageListFragment().apply { arguments = intent.extras }
        setFragment(fragment = fragment, tag = "MESSAGES")
    }
}