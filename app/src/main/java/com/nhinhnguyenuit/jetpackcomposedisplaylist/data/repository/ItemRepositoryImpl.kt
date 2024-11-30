package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.repository

import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.ItemEntity
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local.ItemDao
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(private val dao: ItemDao) : ItemRepository {
    override suspend fun getItems(sortBy: String): List<ItemEntity> = dao.getItems(sortBy)

    override suspend fun insertItems(items: List<ItemEntity>) = dao.insertItems(items)

    override suspend fun deleteItem(item: ItemEntity) = dao.deleteItem(item)
}