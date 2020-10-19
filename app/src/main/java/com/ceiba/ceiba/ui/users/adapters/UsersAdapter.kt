package com.ceiba.ceiba.ui.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.ceiba.databinding.UserListItemBinding
import com.ceiba.ceiba.models.UserBind

class UsersAdapter(private val clickListener: (UserBind) -> Unit) :
    ListAdapter<UserBind,
            UsersAdapter.UserViewHolder>(UserBind.DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user, clickListener)
    }

    class UserViewHolder private constructor(
        private val binding: UserListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserBind, clickListener: (UserBind) -> Unit) {
            binding.user = user

            binding.buttonShowPosts.setOnClickListener {
                clickListener(user)
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserListItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )

                return UserViewHolder(binding)
            }
        }
    }

}