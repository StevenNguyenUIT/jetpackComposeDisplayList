package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model

import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomainModel

fun ItemDomainModel.toEntity(): ItemEntity {
    return ItemEntity(
        index = this.index,
        title = this.title,
        date = this.date,
        description = this.description
    )
}

fun ItemEntity.toDomain(): ItemDomainModel{
    return ItemDomainModel(
        index = this.index,
        title = this.title,
        date = this.date,
        description = this.description
    )
}