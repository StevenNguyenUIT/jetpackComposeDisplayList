package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model

import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomain

fun ItemDomain.toEntity(): ItemEntity {
    return ItemEntity(
        index = this.index,
        title = this.title,
        date = this.date,
        description = this.description
    )
}

fun ItemEntity.toDomain(): ItemDomain {
    return ItemDomain(
        index = this.index,
        title = this.title,
        date = this.date,
        description = this.description
    )
}