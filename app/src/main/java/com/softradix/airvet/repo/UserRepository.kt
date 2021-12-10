package com.softradix.airvet.repo

import com.softradix.airvet.model.UsersModel
import com.softradix.airvet.di.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val webService: WebService) {

    fun getUsers(
        successHandler: (UsersModel) -> Unit, errorBody: (String) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        webService.getUsersList().enqueue(object : Callback<UsersModel> {
            override fun onFailure(call: Call<UsersModel>, t: Throwable?) {
                t?.let {
                    onFailure(it)
                }
            }

            override fun onResponse(
                call: Call<UsersModel>,
                response: Response<UsersModel>
            ) {

                response.body()?.let {
                    successHandler(it)
                }

                response.errorBody()?.let {
                    errorBody("Something went wrong!")
                }
            }

        })
    }
}