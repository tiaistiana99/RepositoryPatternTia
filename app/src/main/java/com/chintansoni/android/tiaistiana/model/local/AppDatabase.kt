package com.chintansoni.android.tiaistiana.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chintansoni.android.tiaistiana.model.local.dao.UserDao
import com.chintansoni.android.tiaistiana.model.local.entity.User

@Database(entities = [User::class], version = DatabaseConstants.mVersion)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}