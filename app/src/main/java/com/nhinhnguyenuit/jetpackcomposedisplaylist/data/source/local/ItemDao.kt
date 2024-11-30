package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.ItemEntity

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY `index` DESC")
    suspend fun getItemsByIndex(): List<ItemEntity>

    @Query("SELECT * FROM items ORDER BY title DESC")
    suspend fun getItemsByTitle(): List<ItemEntity>

    @Query("SELECT * FROM items ORDER BY date DESC")
    suspend fun getItemsByDate(): List<ItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemEntity>)

    @Delete
    suspend fun deleteItem(item: ItemEntity)
}