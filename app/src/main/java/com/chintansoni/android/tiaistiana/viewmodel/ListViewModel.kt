package com.chintansoni.android.tiaistiana.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chintansoni.android.tiaistiana.model.Resource
import com.chintansoni.android.tiaistiana.model.UserRepository
import com.chintansoni.android.tiaistiana.model.local.entity.User
import kotlinx.coroutines.launch

class ListViewModel constructor(private var userRepository: UserRepository) : ViewModel() {

    val userLiveData = MutableLiveData<Resource<List<User>>>()
    private val observer = Observer<Resource<List<User>>> {
        userLiveData.postValue(it)
    }

    init {
        userRepository.userLiveData.observeForever(observer)
    }

    fun refreshUsers() {
        viewModelScope.launch {
            userRepository.refresh()
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            userRepository.fetchUsers(true)
        }
    }

    fun getNextPageUsers() {
        viewModelScope.launch {
            userRepository.getNextPageUsers()
        }
    }

    override fun onCleared() {
        super.onCleared()
        userRepository.userLiveData.removeObserver(observer)
    }
}
