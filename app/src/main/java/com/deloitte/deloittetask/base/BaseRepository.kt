package com.deloitte.deloittetask.base

import com.deloitte.deloittetask.repository.local_data_source.LocalDataSource
import javax.inject.Inject

open class BaseRepository @Inject constructor(protected val localDataSource: LocalDataSource) {

fun clearData(){
    localDataSource.clearUserData()
}

}