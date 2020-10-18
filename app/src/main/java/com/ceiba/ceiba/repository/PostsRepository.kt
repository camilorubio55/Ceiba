package com.ceiba.ceiba.repository

import com.ceiba.ceiba.dto.Post.PostResDTO
import com.ceiba.ceiba.ui.posts.IContractPosts
import com.ceiba.ceiba.webService.CeibaApi
import retrofit2.Response
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val ceibaApi: CeibaApi
) : IContractPosts.Repository {

    override suspend fun getPost(userId : Int) : Response<List<PostResDTO>> {
        return ceibaApi.getPosts(userId)
    }

}