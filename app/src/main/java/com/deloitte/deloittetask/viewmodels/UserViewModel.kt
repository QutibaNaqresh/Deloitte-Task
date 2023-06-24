package com.deloitte.deloittetask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deloitte.deloittetask.base.BaseViewModel
import com.deloitte.deloittetask.repository.UsersRepository
import com.deloitte.deloittetask.repository.models.User
import com.deloitte.deloittetask.ui.user_screen.UserNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(repository: UsersRepository) :
    BaseViewModel<UserNavigator>(repository) {

    private val _userInfo = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userInfo

    fun getUserInfo() {
        viewModelScope.launch {

val user = repository.getUserInfo()
            _userInfo.postValue(user)
        }
    }


}