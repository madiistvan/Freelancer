package com.example.freelancer.data.repository.itemRepositories

import com.example.freelancer.data.model.Item
import com.example.freelancer.data.model.Source
import com.example.freelancer.data.model.UserItem
import com.example.freelancer.data.repository.IRepository
import kotlinx.coroutines.flow.Flow

interface IItemsRepository: IRepository
{
    fun createItem(item: Item, onResult: (Item?) -> Unit): Item?
    suspend fun fetchItems(): Flow<List<Item>>
    fun createSource(source: Source, onResult: (Source?) -> Unit): Source?
}