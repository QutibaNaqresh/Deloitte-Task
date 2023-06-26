package com.deloitte.deloittetask.repository.local_data_source

import com.deloitte.deloittetask.common.Constants
import com.deloitte.deloittetask.repository.local_data_source.models.Article
import com.deloitte.deloittetask.repository.local_data_source.room.UserDao
import com.deloitte.deloittetask.repository.local_data_source.shared_preference.SharedPreferenceHelper
import com.deloitte.deloittetask.repository.local_data_source.models.User
import com.deloitte.deloittetask.repository.local_data_source.room.ArticleDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val userDao: UserDao,
    private val articleDao: ArticleDao,
    private val preferenceHelper: SharedPreferenceHelper
) {

    suspend fun verifyLoginUser(fullName: String, password: String) =
        userDao.verifyLogin(fullName, password)

    suspend fun addUser(user: User) =
        userDao.insertUser(user)

    suspend fun getUserData() = userDao.getUser(getLoggedInUser())

    fun getArticles() = articleDao.getArticles() ?: listOf()

    suspend fun getUserArticle() = articleDao.getUserWithArticles(getLoggedInUser())
    suspend fun updateArticles(newArticles: List<Article>) = articleDao.insertArticles(newArticles)
    fun saveLoggedInUser(uId: Long) {
        preferenceHelper.saveLong(Constants.SharedPreferenceKeys.LOGGED_USER_ID, uId)
    }

    fun getLoggedInUser(): Long =
        preferenceHelper.getLong(Constants.SharedPreferenceKeys.LOGGED_USER_ID)


    fun clearUserData() {
        saveLoggedInUser(-1)

    }
}