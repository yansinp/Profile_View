package com.example.xpay.presentation.home

import com.example.xpay.domain.model.UserListResponse

sealed class HomeState{
    data class SuccessUsersList(val userList:UserListResponse):HomeState()
    data class Error(val error:String) : HomeState()
    object Loading : HomeState()
    object Empty : HomeState()
}
