package com.ceiba.ceiba.repository

import com.ceiba.ceiba.db.dao.UserDao
import com.ceiba.ceiba.db.entities.UserDB
import com.ceiba.ceiba.dto.User.UserResDTO
import com.ceiba.ceiba.ui.users.IContractUsers
import com.ceiba.ceiba.webService.CeibaApi
import retrofit2.Response
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val ceibaApi: CeibaApi,
    private val userDao : UserDao
) : IContractUsers.Repository {

    override suspend fun getUsersRemote(): Response<List<UserResDTO>> {
        return ceibaApi.getUsers()
    }

    override suspend fun getUsersDB(): List<UserDB> {
        return userDao.getUsers()
    }

    override suspend fun insertUsers(userDB: UserDB) {
        userDao.insertUser(userDB)
    }


}