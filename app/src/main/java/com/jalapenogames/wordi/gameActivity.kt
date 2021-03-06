package com.jalapenogames.wordi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import android.support.v4.view.ViewPager
import android.support.v4.view.PagerAdapter
import android.support.v4.app.FragmentManager
import android.widget.Adapter
import android.widget.Toast



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class gameActivity : AppCompatActivity(), topicFragment.OnFragmentInteractionListener,
        scoreFragment.OnFragmentInteractionListener
{

    private var score1 = 0
    private var score2 = 0
    private var score3 = 0
    private var score4 = 0
    private var score5 = 0
    private var score6 = 0
    private var skippedCounter = 0;
    private var mPager: ViewPager? = null
    private var testi = false
    private var mPagerAdapter: PagerAdapter? = null
    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        fullscreen_content.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
        fullscreen_content_controls.visibility = View.VISIBLE
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val mDelayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    override fun onButtonPressed(int: Int) {
        if (int == 0) {
            skippedCounter++;
        }
        else {
            if (int == 1) {
                score1 = score1 + 1 + skippedCounter;

            }
            else if (int == 2) {
                score2 = score2 + 1 + skippedCounter;
            }
            else if (int == 3) {
                score3 = score3 + 1 + skippedCounter;
            }
            else if (int == 4) {
                score4 = score4 + 1 + skippedCounter;
            }
            else if (int == 5) {
                score5 = score5 + 1 + skippedCounter;
            }
            else if (int == 6) {
                score6 = score6 + 1 + skippedCounter;
            }
            skippedCounter = 0
        }

        Thread.sleep(200)
        testi = false
        onTimerToZero(true)

    }

    data class Scores(val s1: Int, val s2: Int, val s3: Int, val s4: Int, val s5: Int, val s6: Int)

    fun scoresToFragment(): Scores {

        return Scores(score1, score2, score3, score4, score5, score6)
    }


    override fun onTimerToZero(boolean: Boolean) {
        val currPos: Int = mPager!!.currentItem
        if (boolean && !testi) {
            testi = true
            mPager?.setCurrentItem(1+currPos, true)

        }
        if (!boolean) {
            testi = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mVisible = true

        // Set up the user interaction to manually show or hide the system UI.
        fullscreen_content.setOnClickListener { toggle() }

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        dummy_button.setOnTouchListener(mDelayHideTouchListener)
        mPager = findViewById(R.id.pager);
        mPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        mPager?.adapter = mPagerAdapter
        mPager?.setPageTransformer(true, ZoomOutPageTransformer())
        //mPager?.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.right)

    }

    override fun onBackPressed() {
        if (mPager?.getCurrentItem() === 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            val i = mPager!!.getCurrentItem() - 1
            mPager?.setCurrentItem(i)
        }
    }



    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        fullscreen_content_controls.visibility = View.GONE
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
        fullscreen_content.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        mVisible = true

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }
}





