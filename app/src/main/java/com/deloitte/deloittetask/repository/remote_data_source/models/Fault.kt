package com.deloitte.deloittetask.repository.remote_data_source.models

import com.google.gson.annotations.SerializedName


data class Fault (

  @SerializedName("faultstring" ) var faultstring : String? = null,
  @SerializedName("detail"      ) var detail      : Detail? = Detail()

)