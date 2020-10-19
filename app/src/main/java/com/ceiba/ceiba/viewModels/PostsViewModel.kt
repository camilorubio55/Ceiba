package com.ceiba.ceiba.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.ceiba.dto.Post.PostResDTO
import com.ceiba.ceiba.models.UserBind
import com.ceiba.ceiba.ui.posts.IContractPosts
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val repository: IContractPosts.Repository
) : ViewModel(), IContractPosts.ViewModel {

    private val _posts = MutableLiveData<UIState>()
    val posts: LiveData<UIState>
        get() = _posts

    private val _user = MutableLiveData<UserBind>()
    val user: LiveData<UserBind>
        get() = _user

    override fun getPost(userId : Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPost(userId)
                _posts.value = if (response.isSuccessful) {
                    UIState.Success(
                        PostResDTO.mapPostResDTOtoPostBind(response.body())
                    )
                } else {
                    UIState.Error(
                        response.errorBody().toString()
                    )
                }
            } catch (ex : Exception) {
                _posts.value = UIState.Error(
                    ex.toString()
                )
            }
        }
    }

    override fun bindInfoUser(user: UserBind) {
        _user.value = user
    }


}