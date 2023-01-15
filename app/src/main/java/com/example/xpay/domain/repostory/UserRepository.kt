package com.example.xpay.domain.repostory

import com.example.xpay.app.util.Resource
import com.example.xpay.domain.model.UserDetailsResponse
import com.example.xpay.domain.model.UserListResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsersList(limit: String, skip: String): Flow<Resource<UserListResponse>>
    fun getUsersDetails(id: String): Flow<Resource<UserDetailsResponse>>

}

