package com.softradix.airvet.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softradix.airvet.model.UsersModel
import com.softradix.airvet.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val apiError = MutableLiveData<String>()
    val onFailure = MutableLiveData<Throwable>()
    val getUsersResponse = MutableLiveData<UsersModel>()
    var isLoading = MutableLiveData<Boolean>()

    fun getUsers() {
        isLoading.value = true
        userRepository.getUsers({
            isLoading.value = false
            getUsersResponse.value = it
        }, {
            isLoading.value = false
            apiError.value = it
        }, {
            isLoading.value = false
            onFailure.value = it
        })
    }

}