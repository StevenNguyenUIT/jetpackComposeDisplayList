package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.repository

import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local.ItemDao
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local.ItemEntity

class ItemRepository(private val dao: ItemDao) {
    suspend fun loadSampleData(items: List<ItemEntity>) = dao.insertAll(items)
    fun getItems(sortBy: String): List<ItemEntity> = dao.getItems(sortBy)
    suspend fun deleteItem(item: ItemEntity) = dao.deleteItem(item)
}