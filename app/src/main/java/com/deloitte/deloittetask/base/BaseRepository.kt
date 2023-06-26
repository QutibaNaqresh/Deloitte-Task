package com.deloitte.deloittetask.base

import com.deloitte.deloittetask.common.Constants
import com.deloitte.deloittetask.repository.local_data_source.LocalDataSource
import com.deloitte.deloittetask.repository.remote_data_source.models.BaseResponse
import com.deloitte.deloittetask.repository.remote_data_source.APIServices
import com.deloitte.deloittetask.repository.remote_data_source.models.ErrorResponse
import com.deloitte.deloittetask.repository.remote_data_source.models.NetworkResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

open class BaseRepository constructor(
    protected val localDataSource: LocalDataSource,
    protected val remoteDataSource: APIServices,
    protected val gson: Gson
) {

    fun clearData() {
        localDataSource.clearUserData()
    }

    protected suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<BaseResponse<T>>): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiToBeCalled()
                val errorResponse = response.errorBody()?.string()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                        when (response.code()) {
                            Constants.NetworkStatusCode.STATUS_OK
                            -> {
                                NetworkResponse.Success(response.body()!!.Result)
                            }

                            Constants.NetworkStatusCode.UNAUTHORIZED -> {
                                NetworkResponse.Failure(
                                    response.code(),
                                    "You are unauthorized!"
                                )
                                // TODO: should logout here

                            }

                            Constants.NetworkStatusCode.INTERNAL_SERVER_ERROR,
                            Constants.NetworkStatusCode.BAD_REQUEST,
                            Constants.NetworkStatusCode.NOT_FOUND,
                            -> {
                                // TODO: Serialize the response to error DTO an display message
                                NetworkResponse.Failure(
                                    response.code(),
                                    "something went wrong!"
                                )

                            }

                            else -> {
                                NetworkResponse.Failure(
                                    response.code(),
                                    "Some thing went wrong!"
                                )
                            }
                        }

                    } else {
                        val errorDto = gson.fromJson<ErrorResponse>(
                            errorResponse,
                            object : TypeToken<ErrorResponse>() {}.type
                        )
                        if (errorDto != null) {
                            NetworkResponse.Failure(
                                response.code(),
                                errorDto.fault?.faultstring ?: response.errorBody()?.string()
                                ?: response.message()
                            )

                        } else {
                            NetworkResponse.Failure(
                                response.code(),
                                "Some thing went wrong!"
                            )
                        }
                    }
                }
            } catch (e: HttpException) {
                e.printStackTrace()
                NetworkResponse.Failure(e.code(), e.message())
            } catch (e: IOException) {
                e.printStackTrace()
                NetworkResponse.Failure(0, "Please check your network connection")

            } catch (e: Exception) {
                e.printStackTrace()
                NetworkResponse.Failure(0, "Some thing went wrong!")
            }
        }
    }


}