package com.example.xpay.data.repostory

import com.example.xpay.app.util.Resource
import com.example.xpay.data.source.UserService
import com.example.xpay.domain.model.UserDetailsResponse
import com.example.xpay.domain.model.UserListResponse
import com.example.xpay.domain.repostory.UserRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserService
) : UserRepository {

    override fun getUsersList(limit: String, skip: String): Flow<Resource<UserListResponse>> =
        flow {
            emit(Resource.Loading)
            service.getUsersList(limit, skip)
                .suspendOnSuccess {
                    val responseDto = this.data
                    val response = responseDto.toUserListResponse()
                    emit(Resource.Success(response))
                }
                .suspendOnError {
                    when (statusCode) {
                        StatusCode.InternalServerError -> emit(Resource.Error(SERVER_ERROR))
                        else -> emit(Resource.Error(INTERNET_ERROR))
                    }
                }
                .suspendOnException {
                    emit(Resource.Error(this.message ?: UNKNOWN_ERROR))
                }
        }

    override fun getUsersDetails(id: String): Flow<Resource<UserDetailsResponse>> = flow {
        emit(Resource.Loading)
        service.getUserDetails(id)
            .suspendOnSuccess {
                val responseDto = this.data
                val response = responseDto.toUser()
                emit(Resource.Success(response))
            }
            .suspendOnError {
                when (statusCode) {
                    StatusCode.InternalServerError -> emit(Resource.Error(SERVER_ERROR))
                    else -> emit(Resource.Error(INTERNET_ERROR))
                }
            }
            .suspendOnException {
                emit(Resource.Error(this.message ?: UNKNOWN_ERROR))
            }

    }


    companion object {
        const val SERVER_ERROR = "Server error"
        const val UNKNOWN_ERROR = "Unknown error"
        const val INTERNET_ERROR = "Error. Please check your internet connection and try again"

    }


}