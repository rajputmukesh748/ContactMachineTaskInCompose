package com.mukesh.contactmachinetask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mukesh.contactmachinetask.BuildConfig
import com.mukesh.contactmachinetask.common.networking.ApiHandler
import com.mukesh.contactmachinetask.common.networking.NetworkInterceptor
import com.mukesh.contactmachinetask.data.repository.RepositoryImpl
import com.mukesh.contactmachinetask.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provide Ok Http Client
     * */
    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(NetworkInterceptor.interceptor)
            .retryOnConnectionFailure(true)
            .build()
    }


    /**
     * Gson Provider
     * */
    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().setLenient().create()


    /**
     * Provide Retrofit Instance
     * */
    @Provides
    @Singleton
    fun providesRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()


    /**
     * Provide Api Params Interface Instance
     * */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiHandler =
        retrofit.create(ApiHandler::class.java)


    /**
     * Repository Instance Provider
     */
    @Provides
    @Singleton
    fun repository(apiHandler: ApiHandler): Repository = RepositoryImpl(apiHandler = apiHandler)

}