package com.deloitte.deloittetask.repository

import com.deloitte.deloittetask.base.BaseRepository
import com.deloitte.deloittetask.common.DateHelper
import com.deloitte.deloittetask.common.ImageFormat
import com.deloitte.deloittetask.repository.local_data_source.LocalDataSource
import com.deloitte.deloittetask.repository.local_data_source.models.Article
import com.deloitte.deloittetask.repository.local_data_source.models.User
import com.deloitte.deloittetask.repository.remote_data_source.APIServices
import com.deloitte.deloittetask.repository.remote_data_source.models.NetworkResponse
import com.deloitte.deloittetask.repository.remote_data_source.models.output_dtos.ArticleDTO
import com.google.gson.Gson
import javax.inject.Inject

class UsersRepository @Inject constructor(
    localDataSource: LocalDataSource,
    remoteDataSource: APIServices,
    gson: Gson
) : BaseRepository(localDataSource, remoteDataSource, gson) {

    suspend fun verifyLoginUser(fullName: String, password: String): Boolean {
        val user = localDataSource.verifyLoginUser(fullName, password)
        if (user != null) {
            localDataSource.saveLoggedInUser(user.uId)
            return true
        }
        return false
    }

    suspend fun addUser(user: User): Long {
        val uId = localDataSource.addUser(user)
        if (!uId.equals(-1))
            localDataSource.saveLoggedInUser(uId)
        return uId
    }

    suspend fun getUserInfo(): User? = localDataSource.getUserData()

    fun hasLoggedInUser(): Boolean = localDataSource.getLoggedInUser() != -1L
    suspend fun getLocalArticle() = localDataSource.getUserArticle()
    suspend fun getArticles(period: Int): NetworkResponse<ArrayList<ArticleDTO>> {
        val result = safeApiCall { remoteDataSource.getArticles(period) }
        var localArticles: List<Article>? = null
        result.data?.let { articleDto ->

            localArticles = articleDto.map {
                Article(uId = localDataSource.getLoggedInUser(),
                articleId =  it.id!!,
                title = it.title!!,
                imageUrl = it.getImage(ImageFormat.STANDARD),
                publishedDate =  DateHelper.calculateTimeDifference(it.publishedDate!!))
            }
            localArticles?.let {
                localDataSource.updateArticles(it)
            }
        }
        return result
    }

}