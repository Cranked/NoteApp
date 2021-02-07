package com.example.noteapp.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.noteapp.R
import com.example.noteapp.data.db.models.User
import com.example.noteapp.ui.base.BaseActivity
import com.example.noteapp.ui.login.LoginActivity
import java.lang.Exception

class SignUpActivity : BaseActivity() {
    lateinit var userEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var insertUserButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
        setOnClickListeners()
    }

    fun init() {
        userEditText = findViewById(R.id.userNameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        insertUserButton = findViewById(R.id.insertUserButton)
    }

    fun setOnClickListeners() {
        insertUserButton.setOnClickListener {
            val userName = userEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (helper.validationNull(userName) || helper.validationNull(password)) {
                showMessage(getString(R.string.mustUserNameandPassword))
                return@setOnClickListener
            }
            var user = User()
            user.userName = userName
            user.password = password
            insertUser(user)
        }
    }

    fun insertUser(user: User) {
        try {
            if (userDao.getUserModel(user.userName).isEmpty()) {
                userDao.insert(user)
                showMessage(getString(R.string.successRegistered))
                goToActivity(Intent(this, LoginActivity::class.java))
            } else {
                showMessage(getString(R.string.alreadyRegistered))
            }
        } catch (e: Exception) {
            showMessage(e.toString())
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}