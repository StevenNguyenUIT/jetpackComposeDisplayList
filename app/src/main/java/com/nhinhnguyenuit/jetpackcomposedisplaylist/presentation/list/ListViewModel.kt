package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.toDomain
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.toEntity
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomain
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.usecase.GetItemsUseCase
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.usecase.LoadSampleDataUseCase
import com.nhinhnguyenuit.jetpackcomposedisplaylist.utils.loadJsonDataFromFile
import com.nhinhnguyenuit.jetpackcomposedisplaylist.utils.parseJsonToDomainModel
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
    application: Application
) : ViewModel() {

    private val context = application.applicationContext

    private val _items = MutableStateFlow<List<ItemDomain>>(emptyList())
    val items: StateFlow<List<ItemDomain>> = _items

    private val _sortBy = MutableStateFlow(SortBy.INDEX)
    val sortBy: StateFlow<SortBy> = _sortBy

    init {
        initData()
    }

    private fun initData() {
        viewModelScope.launch {
            try {
                val existingItems = getItemsUseCase.execute(SortBy.INDEX.query)
                if (existingItems.isEmpty()) {
                    val jsonData = loadJsonDataFromFile(context)
                    val items = parseJsonToDomainModel(jsonData)
                    loadSampleDataUseCase.execute(items.map { it.toEntity() })
                }
                loadItems()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadItems() {
        viewModelScope.launch {
            //Load sample data on first launch
            try {
                val itemList = getItemsUseCase.execute(_sortBy.value.query)
                _items.value = itemList.map { it.toDomain() }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateSortBy(newSortBy: SortBy) {
        _sortBy.value = newSortBy
        loadItems()
    }
}

enum class SortBy(val query: String) {
    INDEX("index"),
    TITLE("title"),
    DATE("date")
}