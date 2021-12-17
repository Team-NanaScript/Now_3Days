package com.now.three_days.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.now.three_days.ui.main.CListFragment
import com.now.three_days.ui.main.RListFragment
import java.lang.IndexOutOfBoundsException

const val RLIST_INDEX = 0;
const val CLIST_INDEX = 1;

class ViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {

    private val pageTabList : Map<Int, ()->Fragment> =
        mapOf(
            RLIST_INDEX to {RListFragment()},
            CLIST_INDEX to {CListFragment()}
        )

    override fun getItemCount(): Int {

        return pageTabList.size
    }

    override fun createFragment(position: Int): Fragment {
        return pageTabList[position]?.invoke() ?:
        throw IndexOutOfBoundsException()
    }


}