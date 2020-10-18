package com.ceiba.ceiba.webService

import com.ceiba.ceiba.utility.Constants.CeibaApi.USERS
import retrofit2.Response
import retrofit2.http.GET

interface CeibaApi {

    @GET(USERS)
    suspend fun getUsers(): Response<Any>


}