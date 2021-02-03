package com.example.noteapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.noteapp.R
import com.example.noteapp.data.db.DatabaseManager
import com.example.noteapp.data.db.dao.UserDao
import com.example.noteapp.data.db.models.User

class LoginActivity : BaseActivity() {
    lateinit var userDao: UserDao
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
        userDao = database!!.userDao()
        signUpButton = findViewById(R.id.signUpButton)
        signInButton = findViewById(R.id.signInButton)
        userNameEditText = findViewById(R.id.editTextUserName)
        passwordEditText = findViewById(R.id.editTextPassword)
    }

    fun setButtonClickListeners() {


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

            } else {
                showMessage(getString(R.string.userpasswordInvalidate))
            }
        }
    }

    fun goToLogin() {
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }

    fun getUserModel(userName: String, password: String): User {
        return userDao.getUserInformations(userName, password)
    }
}