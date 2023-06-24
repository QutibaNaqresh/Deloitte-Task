package com.deloitte.deloittetask.viewmodels

import androidx.lifecycle.viewModelScope
import com.deloitte.deloittetask.base.BaseViewModel
import com.deloitte.deloittetask.repository.UsersRepository
import com.deloitte.deloittetask.ui.splash_screen.SplashNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(usersRepository: UsersRepository) :
    BaseViewModel<SplashNavigator>(usersRepository) {

    fun checkForUserLogin() {
        viewModelScope.launch {
            delay(1500)
            if (repository.hasLoggedInUser())
                getNav()?.openUserPage()
            else
                getNav()?.openNonUserPage()
        }

    }
}