package com.deloitte.deloittetask.viewmodels

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deloitte.deloittetask.base.BaseViewModel
import com.deloitte.deloittetask.repository.UsersRepository
import com.deloitte.deloittetask.repository.local_data_source.models.Article
import com.deloitte.deloittetask.repository.local_data_source.models.User
import com.deloitte.deloittetask.ui.user_screen.UserNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(repository: UsersRepository) :
    BaseViewModel<UserNavigator>(repository) {

    private val _userInfo = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userInfo
    private val _articles = MutableLiveData<ArrayList<Any>>(arrayListOf())
    val articles: LiveData<ArrayList<Any>> = _articles
    val searchText = MutableLiveData("")

    fun getUserInfo() {
        viewModelScope.launch {

            val user = repository.getUserInfo()
            _userInfo.postValue(user)
        }
    }
    private fun getLocalArticle(){
        viewModelScope.launch {

            val articles = repository.getLocalArticle()
            _articles.postValue(articles as ArrayList<Any>)
        }
    }
    fun getMostPopularArticles() {
        clearSearch()
        viewModelScope.launch {
            _isLoading.postValue(true)
            val response = repository.getArticles(30)
            handelApiResponse(response,
                {
                    response.data?.let {

                        getLocalArticle()
                    }
                },
                {
                    getLocalArticle()
                    getNav()?.showMessage("${response.errorMessage}")
                })

            _isLoading.postValue(false)
        }

    }


    fun search(){
        if (searchText.value.isNullOrEmpty())
            getLocalArticle()
        else{
         val articles = _articles.value

            articles?.apply {
                val filteredList  =  (this as ArrayList<Article>).filter {
                  it.title.contains(searchText.value!!,true)
                }
           _articles.value  = filteredList as ArrayList<Any>
            }
         }
        }
    fun clearSearch(){
        searchText.value =""
    }


}