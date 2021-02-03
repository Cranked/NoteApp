package com.example.noteapp.data.db.dao

import androidx.room.*
import com.example.noteapp.data.db.models.User

@Dao
interface UserDao {
    @Query("Select * from user where userName=:usrName AND password=:pwd  ")
    fun getUserInformations(usrName: String, pwd: String): User

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Query("Select * from user where userName=:userName")
    fun getUserModel(userName: String): List<User>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)
}