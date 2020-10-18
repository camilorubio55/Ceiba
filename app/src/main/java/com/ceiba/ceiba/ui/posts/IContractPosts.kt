package com.ceiba.ceiba.ui.posts

import com.ceiba.ceiba.dto.Post.PostResDTO
import com.ceiba.ceiba.models.UserBind
import retrofit2.Response

interface IContractPosts {

    interface ViewModel {
        fun getPost(userId : Int)
        fun bindInfoUser(user : UserBind)
    }

    interface Repository {
        suspend fun getPost(userId : Int) : Response<List<PostResDTO>>
    }

}