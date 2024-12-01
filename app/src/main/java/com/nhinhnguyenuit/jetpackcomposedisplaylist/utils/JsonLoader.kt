package com.nhinhnguyenuit.jetpackcomposedisplaylist.utils

import android.content.Context
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomain
import org.json.JSONArray

fun loadJsonDataFromFile(context: Context): String {
    return context.assets.open("sample_data_list.json").bufferedReader().use { it.readText() }
}

fun parseJsonToDomainModel(json: String): List<ItemDomain> {
    val jsonArray = JSONArray(json)
    val items = mutableListOf<ItemDomain>()
    for (i in 0 until jsonArray.length()) {
        val obj = jsonArray.getJSONObject(i)
        items.add(
            ItemDomain(
                index = obj.getInt("index"),
                title = obj.getString("title"),
                date = obj.getString("date"),
                description = obj.getString("description")
            )
        )
    }
    return items
}