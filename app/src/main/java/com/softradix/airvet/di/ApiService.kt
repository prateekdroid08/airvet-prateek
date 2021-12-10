package com.softradix.airvet.di

import com.softradix.airvet.repo.UserRepository
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

@InstallIn(SingletonComponent::class)
@Module
class ApiService {

    @Singleton //getting instance of repo
    @Provides
    fun provideUserRepository(webService: WebService) = UserRepository(webService)

    @Singleton
    @Provides // retrofit client
    fun provideUserApi(): WebService {
        var okHttpClient: OkHttpClient? = null

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            .create(WebService::class.java)
    }
}