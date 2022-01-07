package com.example.freelancer.data.network

import com.example.freelancer.data.model.*
import retrofit2.Call
import retrofit2.http.*

//private const val BASE_URL = "http://freelancerbackend-env.eba-34kjxuhr.eu-central-1.elasticbeanstalk.com/api/v1/"



/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 * private val retrofit = Retrofit.Builder()
.addConverterFactory(MoshiConverterFactory.create(moshi))
.baseUrl(BASE_URL)
.build()
private val moshi = Moshi.Builder()
.add(KotlinJsonAdapterFactory())
.build()
 */


interface FreelancerAPIService {

    @GET("/api/v1/user")
    suspend fun getAllUsers(@Header("Cookie") token: String): List<UserItem>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/user")
    fun registerUser(@Body userItem: UserItem) : Call<UserItem>

    @GET("/api/v1/jobs")
    suspend fun getJobs(@Header("Cookie") token: String) : List<JobItem>

    @GET("/api/v1/items")
    suspend fun getItems(@Header("Cookie") token: String) : List<Item>


    @Headers("Content-Type: application/json")
    @POST("/api/v1/jobs")
    fun createJob(@Body jobItem: Item, @Header("Cookie") token: String) : Call<JobItem>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/sources")
    fun createSource(@Body source: Source,@Header("Cookie") token: String) : Call<Source>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/items")
    fun createItems(@Body item: Item, @Header("Cookie") token: String) : Call<Item>


    @Headers("Content-Type: application/json")
    @POST("/api/v1/auth")
    fun login(@Body userDTO: UserDTO) : Call<UserDTO>

    @Headers("Content-Type: application/json")
    @PUT("/api/v1/jobs/{id}")
    fun passJob(@Path("id") id:Int, @Header("Cookie") token: String) : Call<JobItem>

    @Headers("Content-Type: application/json")
    @DELETE("/api/v1/jobs/{id}")
    fun deliverJob(@Path("id") id:Int, @Header("Cookie") token: String) : Call<JobItem>
}

//object FreelancerAPI{
//
//    val retrofitService: FreelancerAPIService by lazy { retrofit.create(FreelancerAPIService::class.java) }
//}

