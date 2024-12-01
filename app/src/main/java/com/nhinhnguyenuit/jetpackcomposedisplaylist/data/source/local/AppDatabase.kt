package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.ItemEntity

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}