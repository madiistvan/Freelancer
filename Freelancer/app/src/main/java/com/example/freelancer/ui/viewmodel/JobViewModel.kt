package com.example.freelancer.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.freelancer.data.model.IItem
import com.example.freelancer.data.model.Item
import com.example.freelancer.data.model.JobItem
import com.example.freelancer.utils.RepositoryService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class JobViewModel  : BaseViewModel() {
    private var repository = RepositoryService.getJobRepository()

    fun createJobs(job: Item) {
        viewModelScope.launch {
            repository.createJob(job) {
                if (it?.id != null) {
                    Log.d("createjob", "succes ")

                } else {
                    Log.d("job", "failure ")
                }
            }
        }
    }

    override fun itemClicked(item: IItem) {
        clickedItem = item as JobItem
    }

    override suspend fun fetch() {
        viewModelScope.launch {
            repository.getJobs().collect { jobs -> list.value = jobs }
        }
    }
}