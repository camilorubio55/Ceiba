package com.ceiba.ceiba.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class PostBind(
    val id : Int = 0,
    var title: String = String(),
    var body: String = String()
): Serializable {

    object DiffCallback : DiffUtil.ItemCallback<PostBind>() {
        override fun areItemsTheSame(
            oldItem: PostBind,
            newItem: PostBind
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PostBind,
            newItem: PostBind
        ): Boolean {
            return oldItem == newItem
        }
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserBind

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}