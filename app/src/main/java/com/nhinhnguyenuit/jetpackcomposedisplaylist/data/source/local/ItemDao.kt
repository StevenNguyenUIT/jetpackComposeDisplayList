package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.ItemEntity

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY :sortBy DESC")
    suspend fun getItems(sortBy: String): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemEntity>)

    @Delete
    suspend fun deleteItem(item: ItemEntity)
}