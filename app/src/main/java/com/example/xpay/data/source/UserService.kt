package com.example.xpay.data.source

import com.example.xpay.data.dto.UsersListResponseDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("users")
    suspend fun getUsersList(
        @Query("limit") limit: String,
        @Query("skip") skip: String
    ):ApiResponse<UsersListResponseDto>
}