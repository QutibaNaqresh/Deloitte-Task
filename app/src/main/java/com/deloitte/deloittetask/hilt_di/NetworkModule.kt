package com.deloitte.deloittetask.hilt_di

import com.deloitte.deloittetask.common.Constants
import com.deloitte.deloittetask.repository.remote_data_source.APIServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiServices(okHttpClient: OkHttpClient, gson: Gson): APIServices {
        val retrofit = provideRetrofit(okHttpClient, gson)
        return retrofit.create(APIServices::class.java)
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.Endpoints.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api-key", Constants.Endpoints.API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient
            .Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }


    /* {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.apply {
                connectTimeout(30000, TimeUnit.MILLISECONDS)
                readTimeout(30000, TimeUnit.MILLISECONDS)
                writeTimeout(30000, TimeUnit.MILLISECONDS)
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                addInterceptor(Interceptor { chain: Interceptor.Chain ->
                    val request: Request =
                        if (("${chain.request().url.scheme}://${chain.request().url.host}/") == BuildConfig.GPT_BASE_URL) {
                            chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .build()
                        } else
                            if (!sharedPreference.getString(Constants.SharedPreferenceKeys.TOKEN, "")
                                    .isNullOrEmpty()
                            ) {
                                val newToken = "bearer ${
                                    sharedPreference.getString(
                                        Constants.SharedPreferenceKeys.TOKEN,
                                        ""
                                    )
                                }"
                                chain.request()
                                    .newBuilder()
                                    .addHeader("Content-Type", "application/json")
                                    .addHeader("Authorization", newToken)
                                    .build()
                            } else {
                                chain.request()
                                    .newBuilder()
                                    .addHeader("Content-Type", "application/json")
                                    .build()
                            }
                    chain.proceed(request)
                })
            }

            return okHttpClientBuilder.build()
        }*/
}