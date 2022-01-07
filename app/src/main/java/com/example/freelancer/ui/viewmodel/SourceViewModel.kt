package com.example.freelancer.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freelancer.data.model.Source
import com.example.freelancer.data.model.Item
import com.example.freelancer.data.network.FreelancerApiClient
import com.example.freelancer.utils.RepositoryService
import kotlinx.coroutines.launch

class SourceViewModel: ViewModel() {
    private  var repository = RepositoryService.getItemRepository()

    fun createSource(source: Source, destination: String, properties: String): Source? {
        var result: Source? = null

        viewModelScope.launch {
            result = repository.createSource(source = source) {
                if (it?.id != null) {
                    Log.d("source", "success ")

                    val item = Item(
                        destination = destination,
                        id = 0,
                        properties = properties,
                        source = it,
                        //status = "New"
                    )
                    val itemViewModel = ItemViewModel()
                    itemViewModel.createItem(item)

                } else {
                    Log.d("source", "failure ")
                }
            }
        }
        return result
    }
}