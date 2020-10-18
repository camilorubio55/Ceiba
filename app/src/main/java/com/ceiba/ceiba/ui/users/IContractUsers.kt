package com.ceiba.ceiba.ui.users

import com.ceiba.ceiba.dto.User.UserResDTO
import retrofit2.Response

interface IContractUsers {

    interface ViewModel {
        fun getUsers()
    }

    interface Repository {
        suspend fun getUsers() : Response<List<UserResDTO>>
    }

}