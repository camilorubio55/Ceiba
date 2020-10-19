package com.ceiba.ceiba.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ceiba.ceiba.db.CeibaRoomDatabase
import com.ceiba.ceiba.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): CeibaRoomDatabase {
        return Room.databaseBuilder(context, CeibaRoomDatabase::class.java, "ceiba_database")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                    }
                })
                .allowMainThreadQueries()
                .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(ceibaRoomDatabase: CeibaRoomDatabase): UserDao {
        return ceibaRoomDatabase.userDao()
    }

}