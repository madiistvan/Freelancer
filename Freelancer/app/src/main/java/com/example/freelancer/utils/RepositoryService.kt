package com.example.freelancer.utils

import com.example.freelancer.data.network.FreelancerApiClient
import com.example.freelancer.data.repository.itemRepositories.IItemsRepository
import com.example.freelancer.data.repository.itemRepositories.ItemRepository
import com.example.freelancer.data.repository.jobRepositories.IJobRepository
import com.example.freelancer.data.repository.jobRepositories.JobRepository
import com.example.freelancer.data.repository.userRepositories.IUserRepository
import com.example.freelancer.data.repository.userRepositories.UserRepository
import com.example.freelancer.ui.viewmodel.BaseViewModel
import com.example.freelancer.ui.viewmodel.ItemViewModel
import com.example.freelancer.ui.viewmodel.JobViewModel
import com.example.freelancer.ui.viewmodel.UsersViewModel

object RepositoryService {
    fun getUserRepository(): IUserRepository = UserRepository(FreelancerApiClient.service)
    fun getJobRepository(): IJobRepository = JobRepository(FreelancerApiClient.service)
    fun getItemRepository(): IItemsRepository = ItemRepository(FreelancerApiClient.service)
}