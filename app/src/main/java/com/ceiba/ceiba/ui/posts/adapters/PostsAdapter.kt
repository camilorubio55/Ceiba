package com.ceiba.ceiba.ui.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.ceiba.databinding.PostListItemBinding
import com.ceiba.ceiba.models.PostBind

class PostsAdapter() : ListAdapter<PostBind, PostsAdapter.PostViewHolder>(PostBind.DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    class PostViewHolder private constructor(
        private val binding: PostListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post : PostBind) {
            binding.post = post

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostListItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )

                return PostViewHolder(binding)
            }
        }
    }

}