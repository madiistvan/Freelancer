package com.example.freelancer.data.repository.itemRepositories

import android.util.Log
import com.example.freelancer.utils.ActiveUser
import com.example.freelancer.data.model.Item
import com.example.freelancer.data.model.Source
import com.example.freelancer.data.model.UserItem
import com.example.freelancer.data.network.FreelancerAPIService
import com.example.freelancer.data.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepository(override val freelancerAPi: FreelancerAPIService) :IItemsRepository {
    override fun createItem(item: Item, onResult: (Item?) -> Unit): Item? {
        var result : Item? = null
        try {
            freelancerAPi.createItems(item = item, ActiveUser.token).enqueue(
                object : Callback<Item>{
                    override fun onFailure(call: Call<Item>, t: Throwable) {
                        onResult(null)
                        throw t
                    }
                    override fun onResponse(call: Call<Item>, response: Response<Item>) {
                        Log.d("item creation",response.raw().body.toString())

                        val newItem = response.body()
                        onResult(newItem)
                        if(newItem!=null){
                            result = newItem
                        }
                    }
                }
            )
        }
        catch (exception:Exception){
            Log.d("item creation","Failed " +exception.toString())

            return null
        }
        Log.d("item creation","Succesfull")

        return result
    }

    override suspend fun fetchItems(): Flow<List<Item>> =
        flow {
            val items = freelancerAPi.getItems(ActiveUser.token)
            emit(items)
        }

    override fun createSource(source: Source, onResult: (Source?) -> Unit): Source? {
        var result : Source? = null
        try {
            freelancerAPi.createSource(source = source, ActiveUser.token).enqueue(
                object : Callback<Source> {
                    override fun onFailure(call: Call<Source>, t: Throwable) {
                        onResult(null)
                        throw t
                    }
                    override fun onResponse(call: Call<Source>, response: Response<Source>) {
                        Log.d("Source response",response.raw().toString())
                        Log.d("Source token", ActiveUser.token)

                        val newSource = response.body()
                        onResult(newSource)
                        if (newSource != null) {
                            result =newSource
                        }
                    }
                }
            )
        }
        catch (exception:Exception){
            Log.d("Source creation", "Failed $exception")

            return null
        }
        Log.d("Source creation","Succesfull")

        return result
    }
}