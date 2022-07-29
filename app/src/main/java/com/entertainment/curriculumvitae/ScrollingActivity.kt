package com.entertainment.curriculumvitae

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.entertainment.curriculumvitae.databinding.ActivityScrollingBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        with(binding) {
            appBar.addOnOffsetChangedListener { appBarLayout: AppBarLayout, verticalOffset: Int ->
                when {
                    abs(verticalOffset) == appBarLayout.totalScrollRange -> {
                        //  Collapsed
                        //Hide your TextView here
                        displayToolbar(true)
                    }
                    verticalOffset == 0 -> {
                        //Expanded
                        //Show your TextView here
                        displayToolbar(false)
                    }
                    else -> {
                        //In Between
                        val percentage = abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange
                        tvAppBarTitle.alpha = percentage
                        displayToolbar(false)
                    }

                }
            }
        }
    }

    private fun displayToolbar(show: Boolean) {
        with(binding) {
            if(show) {
                showViewWithAnimation(tvAppBarTitle)
                showViewWithAnimation(civProfilePictureSmall)
            } else {
                hideViewWithAnimation(tvAppBarTitle)
                hideViewWithAnimation(civProfilePictureSmall)
            }
        }
    }

    private fun showViewWithAnimation(view: View) {
        view.visibility = View.VISIBLE
        view.animate()
            .alpha(1.0f)
            .duration = 300
    }

    private fun hideViewWithAnimation(view: View) {
        view.visibility = View.GONE
        view.animate()
            .alpha(0.0f)
            .duration = 300
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}