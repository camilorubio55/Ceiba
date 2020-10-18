package com.ceiba.ceiba.repository

import com.ceiba.ceiba.dto.User.UserResDTO
import com.ceiba.ceiba.ui.users.IContractUsers
import com.ceiba.ceiba.webService.CeibaApi
import retrofit2.Response
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val ceibaApi: CeibaApi
) : IContractUsers.Repository {

    override suspend fun getUsers(): Response<List<UserResDTO>> {
        return ceibaApi.getUsers()
    }


}