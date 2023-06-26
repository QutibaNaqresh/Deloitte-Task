package com.deloitte.deloittetask.repository.remote_data_source.models

import com.google.gson.annotations.SerializedName

data class BaseResponse<out T>(
    @SerializedName("status") val status: String,
    @SerializedName("num_results") val num_results: Int,
    @SerializedName("results") val Result: T
)