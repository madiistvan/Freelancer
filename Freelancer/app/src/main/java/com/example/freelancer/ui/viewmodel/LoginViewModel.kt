package com.example.freelancer.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freelancer.data.model.UserDTO
import com.example.freelancer.utils.RepositoryService
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private  var repository= RepositoryService.getUserRepository()



    fun loginUser(userDTO: UserDTO, onFailure:() -> Any, onSuccess:() -> Any):Boolean{
        var res= true
        viewModelScope.launch {

             repository.login(userDTO) {
                if (it?.equals(null) ?: (true)) {
                    Log.d("login model","failure ")
                    onFailure()

                } else {
                    Log.d("login model","succes ")
                    onSuccess()
                }
            }
        }
        return res
    }

}