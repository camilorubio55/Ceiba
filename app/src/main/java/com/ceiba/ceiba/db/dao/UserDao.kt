package com.ceiba.ceiba.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ceiba.ceiba.db.entities.UserDB

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userDB: UserDB)

    @Query("SELECT * FROM user")
    suspend fun getUsers() : List<UserDB>

}