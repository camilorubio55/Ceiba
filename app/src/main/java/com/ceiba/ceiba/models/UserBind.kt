package com.ceiba.ceiba.models

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class UserBind(
    var id: Int = 0,
    var name: String = String(),
    var email: String = String(),
    var phone: String = String()
): Serializable {

    object DiffCallback : DiffUtil.ItemCallback<UserBind>() {
        override fun areItemsTheSame(
            oldItem: UserBind,
            newItem: UserBind
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserBind,
            newItem: UserBind
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