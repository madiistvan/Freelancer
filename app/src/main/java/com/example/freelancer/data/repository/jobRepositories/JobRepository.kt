package com.example.freelancer.data.repository.jobRepositories

import android.util.Log
import com.example.freelancer.utils.ActiveUser
import com.example.freelancer.data.model.Item
import com.example.freelancer.data.model.JobItem
import com.example.freelancer.data.model.UserItem
import com.example.freelancer.data.network.FreelancerAPIService
import com.example.freelancer.data.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobRepository(override val freelancerAPi: FreelancerAPIService) : IJobRepository {
    override fun passJob(job: JobItem, onResult: (JobItem?) -> Unit) {
        try {
            freelancerAPi.passJob(id = job.id, token = ActiveUser.token)
                .enqueue(
                    object : Callback<JobItem> {
                        override fun onFailure(call: Call<JobItem>, t: Throwable) {
                            onResult(null)
                            throw t
                        }
                        override fun onResponse(call: Call<JobItem>, response: Response<JobItem>) {
                            val responseItem = response.body()
                            onResult(responseItem)
                            Log.d("Job Passing response",response.raw().toString())

                            Log.d("Job Passing response","Succes")
                        }
                    }
                )
            Log.d("Job Passing response","Succes")

        }
        catch (exception:Exception){
            Log.d("Job Passing", "Failed $exception")

        }
    }

    override suspend fun getJobs(): Flow<List<JobItem>> =
        flow {
            val jobs = freelancerAPi.getJobs(ActiveUser.token)
            emit(jobs)
        }

    override fun createJob(jobItem: Item, onResult: (JobItem?) -> Unit): Boolean {
        try {
            freelancerAPi.createJob(jobItem = jobItem, ActiveUser.token).enqueue(
                object : Callback<JobItem>{
                    override fun onFailure(call: Call<JobItem>, t: Throwable) {
                        onResult(null)
                        throw t
                    }
                    override fun onResponse(call: Call<JobItem>, response: Response<JobItem>) {
                        Log.d("job response",response.raw().toString())

                        val newjob = response.body()
                        onResult(newjob)
                    }
                }
            )
        }
        catch (exception:Exception){
            Log.d("item creation","Failed " +exception.toString())

            return false
        }
        Log.d("item creation","Succesfull")

        return true
    }
    override fun delivereJob(job: JobItem, onResult: (JobItem?) -> Unit) {

        try {
            freelancerAPi.deliverJob(job.id, token = ActiveUser.token)
                .enqueue(
                    object : Callback<JobItem>{
                        override fun onFailure(call: Call<JobItem>, t: Throwable) {
                            onResult(null)
                            throw t
                        }
                        override fun onResponse( call: Call<JobItem>, response: Response<JobItem>) {
                            val responseItem = response.body()
                            onResult(responseItem)
                            Log.d("Job delivery response",response.raw().toString())

                            Log.d("Job delivery response","Succes")
                        }
                    }



                )

            Log.d("Job delivery", "succes")

        }
        catch (exception:Exception){
            Log.d("Job delivery", "Failed $exception")

        }

    }

}