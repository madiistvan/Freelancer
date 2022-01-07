package com.example.freelancer.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freelancer.data.model.UserItem
import com.example.freelancer.utils.RepositoryService
import kotlinx.coroutines.launch
class RegisterViewModel: ViewModel() {

    private var repository = RepositoryService.getUserRepository()

    fun registerUser(userItem: UserItem,onSuccess: () ->Any,onFailure: () ->Any):Boolean{
        var res= true
        viewModelScope.launch {

            res= repository.registerUser(userItem = userItem) {
                if (it?.id != null) {
                    Log.d("REGISTER","succes ")
                    onSuccess()
                } else {
                    Log.d("REGISTER","failure ")
                    onFailure()
                }
            }
        }
        return res
    }
}