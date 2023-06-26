package com.deloitte.deloittetask.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deloitte.deloittetask.repository.UsersRepository
import com.deloitte.deloittetask.repository.remote_data_source.models.BaseResponse
import com.deloitte.deloittetask.repository.remote_data_source.models.NetworkResponse
import retrofit2.Response
import javax.inject.Inject

open class BaseViewModel<N : BaseNavigator> @Inject constructor(protected val repository: UsersRepository) :
    ViewModel() {

    private var navigator: N? = null

    protected val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading
    fun setNav(nav: N) {
        this.navigator = nav
    }

    fun getNav() = navigator

    fun signOut() {
        repository.clearData()
        navigator?.restartApp()
    }

    protected fun  <T> handelApiResponse(response: NetworkResponse<T>,toBeCalledOnSuccess: () -> Unit,toBeCalledOnFailure:  () -> Unit){

        when(response){

            is NetworkResponse.Success -> toBeCalledOnSuccess()
            is NetworkResponse.Failure -> toBeCalledOnFailure()

        }
    }
}