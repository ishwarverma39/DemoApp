package com.livtech.demo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.livtech.demo.R
import com.livtech.demo.core.models.TmdbMovie
import com.livtech.demo.databinding.ListItemMovieBinding
import com.livtech.demo.ui.viewholders.DataBoundViewHolder

class MovieListAdapter(
    private val onMovieClick: ((TmdbMovie) -> Unit)?,
    private val onBookMarkClick: ((TmdbMovie, Int) -> Unit)?
) :
    DataBoundListAdapter<TmdbMovie, ListItemMovieBinding>(
        diffCallback = object : DiffUtil.ItemCallback<TmdbMovie>() {
            override fun areItemsTheSame(oldItem: TmdbMovie, newItem: TmdbMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TmdbMovie, newItem: TmdbMovie): Boolean {
                return oldItem.overview.equals(newItem.overview, false)
            }
        }
    ) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ListItemMovieBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_movie,
            parent,
            false
        )
    }

    override fun bind(binding: ListItemMovieBinding, item: TmdbMovie, position : Int) {
        binding.movieItem = item
        binding.root.setOnClickListener { onMovieClick?.invoke(item) }
        binding.bookmarkImageButton.setOnClickListener { onBookMarkClick?.invoke(item, position) }
    }
}