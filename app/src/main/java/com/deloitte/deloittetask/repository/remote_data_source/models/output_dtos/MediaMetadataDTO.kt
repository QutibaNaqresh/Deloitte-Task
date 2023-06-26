package com.deloitte.deloittetask.repository.remote_data_source.models.output_dtos

import com.google.gson.annotations.SerializedName

data class MediaMetadataDTO(
    @SerializedName("url"    ) var url    : String? = null,
    @SerializedName("format" ) var format : String? = null,
    @SerializedName("height" ) var height : Int?    = null,
    @SerializedName("width"  ) var width  : Int?    = null
)
