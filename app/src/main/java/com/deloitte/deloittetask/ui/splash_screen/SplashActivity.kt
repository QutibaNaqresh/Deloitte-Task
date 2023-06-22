package com.deloitte.deloittetask.ui.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.ui.non_user_screen.NonUserActivity
import com.deloitte.deloittetask.ui.user_screen.UserActivity
import java.util.Timer
import java.util.TimerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

            startActivity(Intent(this@SplashActivity,NonUserActivity::class.java))

    }


}