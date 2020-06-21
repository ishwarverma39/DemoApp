package com.livtech.demo.ui.actitivities

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.livtech.demo.R
import com.livtech.demo.ui.fragments.MovieListFragment

class MainActivity : AppBarActivity() {
    override fun getTitleText(): String {
        return resources.getString(R.string.app_name)
    }
    override fun initViews() {
        val fragment = MovieListFragment(R.layout.fragment_movie_list)
        setFragment(fragment, "Movie")
    }

    override fun getMainContainerId(): Int {
        return R.id.mainContainer
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_acitvity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_bookmark -> {
                startActivity(Intent(this, BookmarkedActivity::class.java))
                true
            }
            else -> {
                return true
            }
        }
    }
}