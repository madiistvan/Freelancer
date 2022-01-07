package com.example.freelancer.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.freelancer.data.model.IItem
import com.example.freelancer.data.model.Item
import com.example.freelancer.utils.RepositoryService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ItemViewModel :BaseViewModel()  {
    private var repository = RepositoryService.getItemRepository()

    fun createItem(item: Item):Item?{
        var result:Item? = null

        viewModelScope.launch {
            result = repository.createItem(item = item) {
                if (it?.id != null) {
                    Log.d("item","succes ")

                } else {
                    Log.d("item","failure ")
                }
            }
        }
        Log.d("item",result.toString())
        return result
    }

    override fun itemClicked(item: IItem) {
        clickedItem = item
    }

    override suspend fun fetch() {
        viewModelScope.launch {
            repository.fetchItems().collect { items -> list.value = items }
        }
    }
}