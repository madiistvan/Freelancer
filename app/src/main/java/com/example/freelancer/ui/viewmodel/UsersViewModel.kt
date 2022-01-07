package com.example.freelancer.ui.viewmodel

import androidx.lifecycle.*
import com.example.freelancer.data.model.IItem
import com.example.freelancer.utils.RepositoryService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UsersViewModel() : BaseViewModel() {

    private var repository = RepositoryService.getUserRepository()

    override fun itemClicked(item: IItem) {
        clickedItem = item
    }

    override suspend fun fetch() {
        viewModelScope.launch {
            repository.getAllUsers().collect { users -> list.value = users }
        }
    }
}