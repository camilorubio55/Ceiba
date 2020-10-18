package com.ceiba.ceiba.webService

import com.ceiba.ceiba.dto.Post.PostResDTO
import com.ceiba.ceiba.dto.User.UserResDTO
import com.ceiba.ceiba.utility.Constants.CeibaApi.POSTS
import com.ceiba.ceiba.utility.Constants.CeibaApi.USERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CeibaApi {

    @GET(USERS)
    suspend fun getUsers(): Response<List<UserResDTO>>

    @GET(POSTS)
    suspend fun getPosts(@Query("userId") userId: Int): Response<List<PostResDTO>>

}