package com.example.freelancer.data.repository.userRepositories

import com.example.freelancer.data.model.UserDTO
import com.example.freelancer.data.model.UserItem
import com.example.freelancer.data.repository.IRepository
import kotlinx.coroutines.flow.Flow

interface IUserRepository: IRepository {
    suspend fun getAllUsers(): Flow<List<UserItem>>
    fun registerUser(userItem: UserItem, onResult: (UserItem?) -> Unit):Boolean
    fun login(userDTO: UserDTO, onResult: (UserDTO?) -> Unit): Boolean
}