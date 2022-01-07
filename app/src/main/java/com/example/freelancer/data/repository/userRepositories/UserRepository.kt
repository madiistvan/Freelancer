package com.example.freelancer.data.repository.userRepositories

import android.util.Log
import com.example.freelancer.utils.ActiveUser
import com.example.freelancer.data.model.UserDTO
import com.example.freelancer.data.model.UserItem
import com.example.freelancer.data.network.FreelancerAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(override val freelancerAPi: FreelancerAPIService) : IUserRepository {
    override suspend fun getAllUsers(): Flow<List<UserItem>> =
        flow {
            val users = freelancerAPi.getAllUsers(ActiveUser.token)
            emit(users)
        }

    override fun registerUser(userItem: UserItem, onResult: (UserItem?) -> Unit): Boolean {
        try {
            freelancerAPi.registerUser(userItem = userItem).enqueue(
                object : Callback<UserItem> {
                    override fun onFailure(call: Call<UserItem>, t: Throwable) {
                        onResult(null)
                        throw t
                    }

                    override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
                        val addedUser = response.body()
                        onResult(addedUser)
                    }
                }
            )
        } catch (exception: Exception) {
            Log.d("Registration", "Failed " + exception.toString())

            return false
        }
        Log.d("Registration", "Succesfull")

        return true
    }

    override fun login(userDTO: UserDTO, onResult: (UserDTO?) -> Unit): Boolean {
        try {
            freelancerAPi.login(userDTO = userDTO).enqueue(
                object : Callback<UserDTO> {
                    override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                        onResult(null)
                        throw t
                    }

                    override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                        val user = response.body()
                        Log.d("login sent", userDTO.username)
                        response.headers().get("Set-Cookie")?.let { Log.d("login token", it) }
                        ActiveUser.token = response.headers().get("Set-Cookie").toString()
                        Log.d("login", "Succesfull")
                        onResult(user)
                    }
                }
            )
        } catch (exception: Exception) {
            Log.d("login", "Failed " + exception.toString())

            return false
        }

        return true
    }
}