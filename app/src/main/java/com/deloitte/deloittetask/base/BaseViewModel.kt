package com.deloitte.deloittetask.base

import androidx.lifecycle.ViewModel
import com.deloitte.deloittetask.repository.UsersRepository
import javax.inject.Inject

open class BaseViewModel<N : BaseNavigator> @Inject constructor(protected val repository: UsersRepository) :
    ViewModel() {

    private var navigator: N? = null

    fun setNav(nav: N) {
        this.navigator = nav
    }

    fun getNav() = navigator

    fun signOut() {
        repository.clearData()
        navigator?.onSignedOut()
    }
}