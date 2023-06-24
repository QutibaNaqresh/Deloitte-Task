package com.deloitte.deloittetask.repository

import com.deloitte.deloittetask.base.BaseRepository
import com.deloitte.deloittetask.repository.local_data_source.LocalDataSource
import com.deloitte.deloittetask.repository.local_data_source.room.UserDao
import com.deloitte.deloittetask.repository.models.User
import javax.inject.Inject

class UsersRepository(localDataSource: LocalDataSource) : BaseRepository(localDataSource) {

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
    suspend fun getUserInfo():User? = localDataSource.getUserData()
    fun hasLoggedInUser():Boolean = localDataSource.getLoggedInUser() != -1L

}