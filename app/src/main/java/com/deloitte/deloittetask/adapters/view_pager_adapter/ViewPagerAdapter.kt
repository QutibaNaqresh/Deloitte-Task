package com.deloitte.deloittetask.adapters.view_pager_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter constructor(private val activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    private val fragments: ArrayList<Fragment> = arrayListOf()
    private val fragmentTitles: ArrayList<String> = arrayListOf()

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitles.add(title)
        notifyItemChanged(fragments.size-1)
    }

    fun getFragmentTitle(position:Int) = fragmentTitles[position]


}