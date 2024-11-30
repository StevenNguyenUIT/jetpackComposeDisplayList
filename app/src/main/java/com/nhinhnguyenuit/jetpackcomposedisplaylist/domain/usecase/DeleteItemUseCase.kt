package com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.usecase

import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.ItemEntity
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.repository.ItemRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private val repository: ItemRepository) {
    suspend fun execute(item: ItemEntity) = repository.deleteItem(item)
}