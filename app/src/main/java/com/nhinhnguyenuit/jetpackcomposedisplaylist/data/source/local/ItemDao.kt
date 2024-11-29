package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM itementity ORDER BY :sortBy DESC")
    fun getItems(sortBy: String): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<ItemEntity>)

    @Delete
    fun deleteItem(item: ItemEntity)
}