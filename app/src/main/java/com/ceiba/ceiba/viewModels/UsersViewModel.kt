package com.ceiba.ceiba.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.ceiba.db.entities.UserDB
import com.ceiba.ceiba.dto.User.UserResDTO
import com.ceiba.ceiba.models.UserBind
import com.ceiba.ceiba.ui.users.IContractUsers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val repository : IContractUsers.Repository
) : ViewModel(), IContractUsers.ViewModel {

    private val _users = MutableLiveData<UIState>()
    val users: LiveData<UIState>
        get() = _users

    private val _usersByFilter = MutableLiveData<List<UserBind>>()
    val usersByFilter: MutableLiveData<List<UserBind>>
        get() = _usersByFilter

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
                                val listUserBind = UserResDTO.mapUserResDTOtoUserBind(listUserResDTO)
                                _usersByFilter.value = listUserBind
                                UIState.Success(listUserBind)
                            }

                        } else {
                            UIState.Error(
                                response.errorBody().toString()
                            )
                        }
                    }
                    else -> {
                        val listUserBind = UserResDTO.mapUserDBtoUserBind(repository.getUsersDB())
                        _usersByFilter.value = listUserBind
                        _users.value = UIState.Success(listUserBind)
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

    override fun filterUsers(newText: String) : List<UserBind>? {
        return _usersByFilter.value?.filter { userBind ->
            userBind.name.trim().toLowerCase(Locale.ROOT).contains(
                newText.trim().toLowerCase(
                    Locale.ROOT
                )
            )
        }
    }

}