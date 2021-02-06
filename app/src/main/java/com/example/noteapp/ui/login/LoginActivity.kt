package com.example.noteapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.noteapp.R
import com.example.noteapp.data.db.models.User
import com.example.noteapp.ui.main.MainActivity
import com.example.noteapp.ui.signup.SignUpActivity
import com.example.noteapp.ui.base.BaseActivity
import java.lang.Exception

class LoginActivity : BaseActivity() {
    lateinit var signUpButton: Button
    lateinit var signInButton: Button
    lateinit var userNameEditText: EditText
    lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        setButtonClickListeners()


    }

    fun init() {
        try {
            signUpButton = findViewById(R.id.signUpButton)
            signInButton = findViewById(R.id.signInButton)
            userNameEditText = findViewById(R.id.editTextUserName)
            passwordEditText = findViewById(R.id.editTextPassword)
        } catch (e: Exception) {
            showMessage(e.toString())
        }
    }

    fun setButtonClickListeners() {
        try {
            signUpButton.setOnClickListener {
                goToLogin()
            }
            signInButton.setOnClickListener {
                if (helper.validationNull(userNameEditText.text.toString()) || helper.validationNull(passwordEditText.text.toString())) {
                    showMessage(getString(R.string.mustUserNameandPassword))
                    return@setOnClickListener
                }
                var user = getUserModel(userNameEditText.text.toString(), passwordEditText.text.toString())
                if (user != null) {
                    clearUserActivateState()
                    user.isActivated = true
                    userDao.update(user)
                    goToActivity()
                } else {
                    showMessage(getString(R.string.userpasswordInvalidate))
                }
            }
        } catch (e: Exception) {
            showMessage(e.toString())
        }
    }

    fun goToLogin() {
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }

    fun goToActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun clearUserActivateState() {
        userDao.getAllUser().forEach {
            it.isActivated = false
            userDao.update(it)
        }
    }

    fun getUserModel(userName: String, password: String): User {
        return userDao.getUserInformations(userName, password)
    }
}