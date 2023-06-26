package com.deloitte.deloittetask.repository.remote_data_source

import com.deloitte.deloittetask.common.Constants
import com.deloitte.deloittetask.repository.remote_data_source.models.BaseResponse
import com.deloitte.deloittetask.repository.remote_data_source.models.output_dtos.ArticleDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServices {

    @GET(Constants.Endpoints.VIEWED_ARTICLES)
    suspend fun getArticles(@Path("period") period: Int=1, @Query("api-key") apiKey: String = Constants.Endpoints.API_KEY): Response<BaseResponse<ArrayList<ArticleDTO>>>

}