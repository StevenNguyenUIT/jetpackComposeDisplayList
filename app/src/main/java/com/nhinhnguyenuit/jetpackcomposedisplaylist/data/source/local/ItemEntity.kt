package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey
    val index: Int,
    val title: String,
    val date: String,
    val description: String
)
