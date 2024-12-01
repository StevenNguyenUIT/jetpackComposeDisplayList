package com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.usecase

import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repository: ItemRepository) {
    suspend fun execute(sortBy: String) = repository.getItems(sortBy)
}