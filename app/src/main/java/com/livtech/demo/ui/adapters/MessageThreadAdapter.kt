package com.livtech.demo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.livtech.demo.R
import com.livtech.demo.core.models.BranchMessage
import com.livtech.demo.databinding.ListItemThreadBinding

class MessageThreadAdapter(private val onItemClick: (BranchMessage, Int) -> Unit) :
    DataBoundListAdapter<BranchMessage, ListItemThreadBinding>(
        diffCallback = object : DiffUtil.ItemCallback<BranchMessage>() {
            override fun areItemsTheSame(oldItem: BranchMessage, newItem: BranchMessage): Boolean {
                return oldItem.threadId == newItem.threadId
            }

            override fun areContentsTheSame(
                oldItem: BranchMessage,
                newItem: BranchMessage
            ): Boolean {
                return oldItem.timestamp.equals(newItem.timestamp, false)
            }
        }
    ) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemThreadBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_thread,
            parent,
            false
        )
    }

    override fun bind(binding: ListItemThreadBinding, item: BranchMessage, position: Int) {
        binding.message = item
        binding.root.setOnClickListener { onItemClick.invoke(item, position) }
    }
}