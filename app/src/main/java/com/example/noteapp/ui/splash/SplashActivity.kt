package com.example.noteapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.noteapp.R
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.base.View
import com.example.noteapp.ui.login.LoginActivity
import com.example.noteapp.ui.main.MainActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val user = userDao.getActivateUser(true)
            if (user != null) {
                activeUserId = user.userId
                goToActivity(Intent(this, MainActivity::class.java))
            } else {
                goToActivity(Intent(this, LoginActivity::class.java))
            }
        }, 500)

    }
}