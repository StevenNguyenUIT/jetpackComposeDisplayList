package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.Item

class SampleDataLoader(private val context: Context) {
    fun loadSampleData(): List<Item> {
        val json =
            context.assets.open("sample_data_list.json").bufferedReader().use { it.readText() }
        val itemType = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(json, itemType)
    }
}