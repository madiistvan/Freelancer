package com.example.freelancer.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freelancer.data.model.IItem
import kotlinx.coroutines.launch
abstract class BaseViewModel : ViewModel() {
    abstract fun itemClicked(item: IItem): Unit
    abstract suspend fun fetch()

    val list: MutableLiveData<List<IItem>> by lazy {
        MutableLiveData<List<IItem>>().also {
            viewModelScope.launch { fetch() }
        }
    }

    lateinit var clickedItem: IItem

}