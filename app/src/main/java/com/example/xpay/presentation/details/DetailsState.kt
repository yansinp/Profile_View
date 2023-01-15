package com.example.xpay.presentation.details

import com.example.xpay.domain.model.UserDetailsResponse

sealed class DetailsState {

    data class SuccessUsersDetails(val userDetails: UserDetailsResponse) : DetailsState()
    data class Error(val error: String) : DetailsState()
    object Loading : DetailsState()
    object Empty : DetailsState()
}