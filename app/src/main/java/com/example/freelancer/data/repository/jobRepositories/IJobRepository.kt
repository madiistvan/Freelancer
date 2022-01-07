package com.example.freelancer.data.repository.jobRepositories

import com.example.freelancer.data.model.Item
import com.example.freelancer.data.model.JobItem
import com.example.freelancer.data.model.UserItem
import com.example.freelancer.data.repository.IRepository
import kotlinx.coroutines.flow.Flow

interface IJobRepository : IRepository {
    fun passJob(job: JobItem, onResult: (JobItem?) -> Unit)
    suspend fun getJobs(): Flow<List<JobItem>>
    fun createJob(jobItem: Item, onResult: (JobItem?) -> Unit): Boolean
    fun delivereJob(job: JobItem,onResult: (JobItem?) -> Unit)
}