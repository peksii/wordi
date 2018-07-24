package com.jalapenogames.wordi

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import java.nio.file.Files.size
import java.nio.file.Files.size
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter


class ViewPagerAdapter internal constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val COUNT = 20

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = topicFragment()
            1 -> fragment = letterFragment()
            2 -> fragment = topicFragment()
            3 -> fragment = letterFragment()
            4 -> fragment = topicFragment()
            5 -> fragment = letterFragment()
            6 -> fragment = topicFragment()
            7 -> fragment = letterFragment()
            8 -> fragment = topicFragment()
            9 -> fragment = letterFragment()
            10 -> fragment = topicFragment()
            11 -> fragment = letterFragment()
            12 -> fragment = topicFragment()
            13 -> fragment = letterFragment()
            14 -> fragment = topicFragment()
            15 -> fragment = letterFragment()
            16 -> fragment = topicFragment()
            17 -> fragment = letterFragment()
            18 -> fragment = topicFragment()
            19 -> fragment = letterFragment()
        }

        return fragment
    }

    override fun getCount(): Int {
        return COUNT
    }



    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab " + (position + 1)
    }
}
