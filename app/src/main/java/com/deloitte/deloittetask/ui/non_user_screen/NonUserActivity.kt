package com.deloitte.deloittetask.ui.non_user_screen

import androidx.activity.viewModels
import com.deloitte.deloittetask.BR
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.adapters.view_pager_adapter.ViewPagerAdapter
import com.deloitte.deloittetask.base.BaseActivity
import com.deloitte.deloittetask.databinding.ActivityNonUserBinding
import com.deloitte.deloittetask.ui.non_user_screen.fragments.LoginFragment
import com.deloitte.deloittetask.ui.non_user_screen.fragments.RegisterFragment
import com.deloitte.deloittetask.viewmodels.NonUserViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/*
*
*  Created by Qutiba 21/6/2023
*  This is the activity that holds the @LoginFragment and @RegisterFragment
* */
@AndroidEntryPoint
class NonUserActivity : BaseActivity<NonUserViewModel, ActivityNonUserBinding>(), NonUserNavigator {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val viewmodel by viewModels<NonUserViewModel>()

    override fun initialize() {
        super.initialize()
        viewmodel.setNav(this)
        supportActionBar?.title = getString(R.string.txt_welcome)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(LoginFragment.newInstance(), LoginFragment.TAG)
        viewPagerAdapter.addFragment(RegisterFragment.newInstance(), RegisterFragment.TAG)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tapLayout, binding.viewPager) { tab, position ->

            tab.text = viewPagerAdapter.getFragmentTitle(position)
        }.attach()

    }

    override fun getLayoutRes(): Int = R.layout.activity_non_user

    override fun getViewModel(): NonUserViewModel = viewmodel

    override fun getBindingVariable(): Int = BR.viewModel
}