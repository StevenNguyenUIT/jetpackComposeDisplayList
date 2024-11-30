package com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.repository

import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.ItemEntity

interface ItemRepository {
    suspend fun getItems(sortBy: String): List<ItemEntity>
    suspend fun insertItems(items: List<ItemEntity>)
    suspend fun deleteItem(item: ItemEntity)
}