package com.deloitte.deloittetask.repository.remote_data_source.models
/*
*
*  CREATED BY QUTIBA AL-NAQRISH  25/6/2023
*
*  This class is used to wrap api response
*/
sealed class NetworkResponse<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : NetworkResponse<T>(data)
    class Failure<T>(error: Int,errorMessage:String) : NetworkResponse<T>(errorMessage = errorMessage)
}
