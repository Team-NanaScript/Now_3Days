package com.now.three_days.adapter


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.now.three_days.ui.main.MainCFragment
import com.now.three_days.ui.main.MainRFragment

const val MCLIST_INDEX = 0;
const val MRLIST_INDEX = 1;


class ViewPagerMainAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val pageTabList: Map<Int, () -> Fragment> =
        mapOf(
            MCLIST_INDEX to { MainCFragment() },
            MRLIST_INDEX to { MainRFragment() }
        )

    override fun getItemCount(): Int {

        return pageTabList.size
    }

    override fun createFragment(position: Int): Fragment {
        return pageTabList[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }


}