package com.example.freelancer.data.network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object FreelancerApiClient {
    //v1 vissza
    const val BASE_URL =
        "http://freelancerbackend-env.eba-34kjxuhr.eu-central-1.elasticbeanstalk.com"

    private var retrofit: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
    var service: FreelancerAPIService = retrofit.build().create(FreelancerAPIService::class.java)


    fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())


    }

}






