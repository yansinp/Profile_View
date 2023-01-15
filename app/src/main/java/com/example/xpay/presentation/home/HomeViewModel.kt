package com.example.xpay.presentation.home

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
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Empty)
    val homeState = _homeState.asStateFlow()

    fun getUsersList(limit: String, skip:String) = viewModelScope.launch {
        userRepository.getUsersList(limit, skip).collect {
            when (it) {
                is Resource.Success -> _homeState.emit(HomeState.SuccessUsersList(it.value))
                is Resource.Error -> _homeState.emit(HomeState.Error(it.error))
                is Resource.Loading -> _homeState.emit(HomeState.Loading)
            }
        }

    }

}