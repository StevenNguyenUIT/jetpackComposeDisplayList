package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.toDomain
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.toEntity
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomainModel
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.usecase.GetItemsUseCase
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.usecase.LoadSampleDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val loadSampleDataUseCase: LoadSampleDataUseCase,
    private val application: Application
) : ViewModel() {

    private val context = application.applicationContext

    private val _items = MutableStateFlow<List<ItemDomainModel>>(emptyList())
    val items: StateFlow<List<ItemDomainModel>> = _items

    // Default sorting criteria
    private var sortBy = "index"

    init {
        initData()
    }

    private fun loadJsonDataFromFile(): String {
        return context.assets.open("sample_data_list.json").bufferedReader().use { it.readText() }
    }

    private fun parseJsonToDomainModel(json: String): List<ItemDomainModel> {
        val jsonArray = JSONArray(json)
        val items = mutableListOf<ItemDomainModel>()
        for (i in 0 until jsonArray.length()){
            val obj = jsonArray.getJSONObject(i)
            items.add(
                ItemDomainModel(
                    index = obj.getInt("index"),
                    title = obj.getString("title"),
                    date = obj.getString("date"),
                    description = obj.getString("description")
                )
            )
        }
        return items
    }

    private fun initData() {

        viewModelScope.launch {

            try {
                val existingItems = getItemsUseCase.execute("index")
                Log.d("stevenn", "oke")
                if(existingItems.isEmpty()){
                    val jsonData = loadJsonDataFromFile()
                    val items = parseJsonToDomainModel(jsonData)
                    loadSampleDataUseCase.execute(items.map { it.toEntity() })
                }
                loadItems()
            } catch (e: Exception){
                e.printStackTrace()
            }

        }
    }


    fun loadItems() {
        Log.d("stevenn", "LoadItems + ${sortBy}")
        viewModelScope.launch {
            //Load sample data on first launch
            try {
                val itemList = getItemsUseCase.execute(sortBy)
                _items.value = itemList.map { it.toDomain() }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setSortingCriteria(criteria: String){
        sortBy = criteria
        loadItems()
    }
}