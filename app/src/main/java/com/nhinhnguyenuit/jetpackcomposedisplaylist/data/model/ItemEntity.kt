package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey
    val index: Int,
    val title: String,
    val date: String,
    val description: String
)
