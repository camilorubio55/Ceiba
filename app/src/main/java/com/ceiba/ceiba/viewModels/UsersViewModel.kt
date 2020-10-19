package com.ceiba.ceiba.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.ceiba.db.entities.UserDB
import com.ceiba.ceiba.dto.User.UserResDTO
import com.ceiba.ceiba.ui.users.IContractUsers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val repository : IContractUsers.Repository
) : ViewModel(), IContractUsers.ViewModel {

    private val _users = MutableLiveData<UIState>()
    val users: LiveData<UIState>
        get() = _users

    override fun getUsers() {
        viewModelScope.launch {
            try {
                when(repository.getUsersDB().count()) {
                    0 -> {
                        val response = repository.getUsersRemote()
                        _users.value = if (response.isSuccessful) {
                            response.body()?.let { listUserResDTO ->
                                insertUsers(
                                    UserResDTO.mapUserResDTOtoUserDB(listUserResDTO)
                                )
                                UIState.Success(
                                    UserResDTO.mapUserResDTOtoUserBind(listUserResDTO)
                                )
                            }

                        } else {
                            UIState.Error(
                                response.errorBody().toString()
                            )
                        }
                    }
                    else -> {
                        _users.value = UIState.Success(
                            UserResDTO.mapUserDBtoUserBind(repository.getUsersDB())
                        )
                    }
                }
            } catch (ex : Exception) {
                _users.value = UIState.Error(
                    ex.toString()
                )
            }
        }
    }

    override suspend fun insertUsers(listUserDB: List<UserDB>) {
        listUserDB.forEach { userDB ->
            repository.insertUsers(userDB)
        }
    }


}