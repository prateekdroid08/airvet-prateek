package com.softradix.airvet.di

import com.softradix.airvet.model.UsersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface WebService {
    @GET("api/")
    fun getUsersList(): Call<UsersModel>

}