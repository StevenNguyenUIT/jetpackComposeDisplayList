package com.nhinhnguyenuit.jetpackcomposedisplaylist.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.toDomain
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.model.toEntity
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.model.ItemDomain
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.usecase.DeleteItemUseCase
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    private val _item = MutableStateFlow<ItemDomain?>(null)
    val item: StateFlow<ItemDomain?> = _item

    fun loadItem(itemId: Int) {
        viewModelScope.launch {
            try {
                val items = getItemsUseCase.execute("index")
                _item.value = items.map { it.toDomain() }.find { it.index == itemId }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteItem(item: ItemDomain?) {
        viewModelScope.launch {
            try {
                if (item != null) {
                    deleteItemUseCase.execute(item.toEntity())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}