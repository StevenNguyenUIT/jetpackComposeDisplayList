package com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.ItemEntity

class SampleDataLoader(private val context: Context) {
    fun loadSampleData(): List<ItemEntity> {
        val json =
            context.assets.open("sample_data_list.json").bufferedReader().use { it.readText() }
        val itemType = object : TypeToken<List<ItemEntity>>() {}.type
        return Gson().fromJson(json, itemType)
    }
}