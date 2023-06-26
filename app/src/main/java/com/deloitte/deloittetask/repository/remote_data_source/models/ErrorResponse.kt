package com.deloitte.deloittetask.repository.remote_data_source.models

import com.google.gson.annotations.SerializedName


data class ErrorResponse (

  @SerializedName("fault" ) var fault : Fault? = Fault()

)