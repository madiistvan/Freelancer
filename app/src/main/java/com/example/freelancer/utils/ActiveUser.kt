package com.example.freelancer.utils

import com.example.freelancer.data.model.UserItem

object ActiveUser{
    fun getActiveUser():UserItem{
        return user
    }
    lateinit var user: UserItem
    lateinit var token:String
}