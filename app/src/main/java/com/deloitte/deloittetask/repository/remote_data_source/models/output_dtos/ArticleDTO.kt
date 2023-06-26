package com.deloitte.deloittetask.repository.remote_data_source.models.output_dtos

import com.deloitte.deloittetask.common.ImageFormat
import com.google.gson.annotations.SerializedName

data class ArticleDTO(
    @SerializedName("uri") var uri: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("id") var id: Long? = null,
    @SerializedName("asset_id") var assetId: Long? = null,
    @SerializedName("source") var source: String? = null,
    @SerializedName("published_date") var publishedDate: String? = null,
    @SerializedName("updated") var updated: String? = null,
    @SerializedName("section") var section: String? = null,
    @SerializedName("subsection") var subsection: String? = null,
    @SerializedName("nytdsection") var nytdsection: String? = null,
    @SerializedName("adx_keywords") var adxKeywords: String? = null,
    @SerializedName("column") var column: String? = null,
    @SerializedName("byline") var byline: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("abstract") var abstract: String? = null,
    @SerializedName("des_facet") var desFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("org_facet") var orgFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("per_facet") var perFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("geo_facet") var geoFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("media") var media: ArrayList<MediaDTO> = arrayListOf(),
    @SerializedName("eta_id") var etaId: Int? = null
) {
    fun getImage(imageFormat: ImageFormat): String =
        if (media.isNotEmpty()) {
            media[0].mediaMetadata.find {
                it.format == imageFormat.value
            }?.url ?: ""


        } else {
            ""
        }

}
