package com.deloitte.deloittetask.ui.user_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.deloitte.deloittetask.BR
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.base.BaseActivity
import com.deloitte.deloittetask.databinding.ActivityUserBinding
import com.deloitte.deloittetask.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : BaseActivity<UserViewModel, ActivityUserBinding>(),UserNavigator {

    private lateinit var navController: NavController
    private val userViewModel by viewModels<UserViewModel>()
    override fun initialize() {
        super.initialize()
        userViewModel.setNav(this)
        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        binding.bottomNav.setupWithNavController(navController)

    }

    override fun getLayoutRes(): Int = R.layout.activity_user

    override fun getViewModel(): UserViewModel = userViewModel

    override fun getBindingVariable(): Int = BR.viewModel
}