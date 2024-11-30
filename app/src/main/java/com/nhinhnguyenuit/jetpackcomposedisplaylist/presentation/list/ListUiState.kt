package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list

import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomainModel

data class ListUiState(
    val items: List<ItemDomainModel> = emptyList()
)
