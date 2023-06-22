package com.deloitte.deloittetask.ui.non_user_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.adapters.view_pager_adapter.ViewPagerAdapter
import com.deloitte.deloittetask.databinding.ActivityNonUserBinding
import com.deloitte.deloittetask.ui.non_user_screen.fragments.LoginFragment
import com.deloitte.deloittetask.ui.non_user_screen.fragments.RegisterFragment
import com.google.android.material.tabs.TabLayoutMediator

/*
*
*  Created by Qutiba 21/6/2023
*  This is the activity that holds the @LoginFragment and @RegisterFragment
* */
class NonUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNonUserBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_non_user)
        initialize()
    }


    private fun initialize() {

        supportActionBar?.title = getString(R.string.txt_welcome)
        viewPagerAdapter = ViewPagerAdapter(this)

        viewPagerAdapter.addFragment(LoginFragment.newInstance(), LoginFragment.TAG)
        viewPagerAdapter.addFragment(RegisterFragment.newInstance(), RegisterFragment.TAG)

        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tapLayout, binding.viewPager) { tab, position ->

            tab.text = viewPagerAdapter.getFragmentTitle(position)
        }.attach()

    }
}