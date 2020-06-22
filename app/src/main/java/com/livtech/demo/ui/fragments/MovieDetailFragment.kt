package com.livtech.demo.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.livtech.demo.R
import com.livtech.demo.core.models.TmdbMovie
import com.livtech.demo.databinding.FragmentMovieDetailBinding
import com.livtech.demo.ui.viewmodels.MovieDetailViewModel


class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    lateinit var fragmentMovieDetailBinding: FragmentMovieDetailBinding
    private var tmdbMovie: TmdbMovie? = null
    private lateinit var bookmarkMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initViewModels() {
        movieDetailViewModel =
            ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(
                MovieDetailViewModel::class.java
            )
    }

    override fun bindView(viewDataBinding: FragmentMovieDetailBinding) {
        fragmentMovieDetailBinding = viewDataBinding
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        fragmentMovieDetailBinding.showLoading = true
        initWebView()
        initDetail()
        arguments?.let {
            val movieID = it.getInt("MOVIE_ID")
            movieDetailViewModel.fetchMovieDetail(movieID)
        }
    }

    private fun initWebView() {
        fragmentMovieDetailBinding.webView.let {
            it.webViewClient = MyWebViewClient()
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            webView: WebView,
            url: String
        ): Boolean {
            return false
        }

        override fun onPageCommitVisible(view: WebView?, url: String?) {
            super.onPageCommitVisible(view, url)
            fragmentMovieDetailBinding.showLoading = false
        }
    }

    private fun initDetail() {
        movieDetailViewModel.movieDetailData.observe(viewLifecycleOwner, Observer {
            fragmentMovieDetailBinding.resource = it
            tmdbMovie = it.data
            setBookmark()
            loadWebPage()
        })
    }

    private fun loadWebPage() {
        tmdbMovie?.movieIdDetail?.let {
            val url = "https://www.imdb.com/title/${it.imdb_id}"
            fragmentMovieDetailBinding.webView.loadUrl(url)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_acitvity_menu, menu)
        bookmarkMenuItem = menu.findItem(R.id.menu_item_bookmark)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_bookmark -> {
                tmdbMovie?.let {
                    it.bookmarked = !it.bookmarked
                    movieDetailViewModel.onBookMarkClick(it)
                }
                setBookmark()
            }
        }
        return true
    }

    private fun setBookmark() {
        tmdbMovie?.let {
            if (it.bookmarked) bookmarkMenuItem.setIcon(R.drawable.ic_action_bookmark_activated)
            else bookmarkMenuItem.setIcon(R.drawable.ic_action_bookmark_normal)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_movie_detail
    }
}