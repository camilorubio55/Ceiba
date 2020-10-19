package com.ceiba.ceiba.ui.users

import com.ceiba.ceiba.db.entities.UserDB
import com.ceiba.ceiba.dto.User.UserResDTO
import com.ceiba.ceiba.models.UserBind
import retrofit2.Response

interface IContractUsers {

    interface ViewModel {
        fun getUsers()
        suspend fun insertUsers(listUserDB: List<UserDB>)
        fun filterUsers(newText : String) : List<UserBind>?
    }

    interface Repository {
        suspend fun getUsersRemote() : Response<List<UserResDTO>>
        suspend fun getUsersDB() : List<UserDB>
        suspend fun insertUsers(userDB: UserDB)
    }

}