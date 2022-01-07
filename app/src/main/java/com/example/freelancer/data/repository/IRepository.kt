package com.example.freelancer.data.repository

import com.example.freelancer.data.network.FreelancerAPIService

interface IRepository {
    val freelancerAPi: FreelancerAPIService
    sealed class Result {
        data class Success(val list : List<Any>) :Result()
        data class Failure(val throwable: Throwable): Result()
    }
}