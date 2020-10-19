package com.ceiba.ceiba.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceiba.ceiba.db.dao.UserDao
import com.ceiba.ceiba.db.entities.UserDB

@Database(
    entities = [
        UserDB::class
    ],
    version = 1
)
abstract class CeibaRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}