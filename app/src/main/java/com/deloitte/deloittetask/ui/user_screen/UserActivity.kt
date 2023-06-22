package com.deloitte.deloittetask.ui.user_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding :ActivityUserBinding
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user)

        navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        binding.bottomNav.setupWithNavController(navController)
    }
}