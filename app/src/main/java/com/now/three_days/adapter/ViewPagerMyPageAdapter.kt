package com.now.three_days.adapter


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.now.three_days.ui.list.CListFragment
import com.now.three_days.ui.list.RListFragment
import com.now.three_days.ui.mypage.MyCFragment
import com.now.three_days.ui.mypage.MyRFragment

const val MY_CLIST_INDEX = 0;
const val MY_RLIST_INDEX = 1;


class ViewPagerMyPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val pageTabList: Map<Int, () -> Fragment> =
        mapOf(
            MY_RLIST_INDEX to { MyCFragment() },
            MY_CLIST_INDEX to { MyRFragment() }
        )

    override fun getItemCount(): Int {

        return pageTabList.size
    }

    override fun createFragment(position: Int): Fragment {
        return pageTabList[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }


}