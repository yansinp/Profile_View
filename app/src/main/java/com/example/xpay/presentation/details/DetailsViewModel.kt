package com.example.xpay.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xpay.app.util.Resource
import com.example.xpay.domain.repostory.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<String>("id")

    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Empty)
    val detailsState = _detailsState.asStateFlow()

    init {
        viewModelScope.launch {
            if (id != null) {
                getUsersList(id)
            } else {
                _detailsState.emit(DetailsState.Error("Unable to fetch details!"))
            }
        }
    }

    private fun getUsersList(id: String) = viewModelScope.launch() {
        userRepository.getUsersDetails(id).collect() {
            when (it) {
                is Resource.Loading -> _detailsState.emit(DetailsState.Loading)
                is Resource.Success -> _detailsState.emit(DetailsState.SuccessUsersDetails(it.value))
                is Resource.Error -> _detailsState.emit(DetailsState.Error(it.error))
            }
        }
    }

}