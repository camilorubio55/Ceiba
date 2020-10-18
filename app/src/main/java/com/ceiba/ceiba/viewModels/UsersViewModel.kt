package com.ceiba.ceiba.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            val response = repository.getUsers()
            _users.value = if (response.isSuccessful) {
                UIState.Success(
                    UserResDTO.mapUserResDTOtoUserBind(response.body())
                )
            } else {
                UIState.Error(
                    response.errorBody().toString()
                )
            }
        }
    }


}