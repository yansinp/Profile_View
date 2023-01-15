package com.example.xpay.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.xpay.databinding.UserListItemBinding
import com.example.xpay.domain.model.UserListResponse


class UsersListAdapter(private val listener:OnItemClick) : ListAdapter<UserListResponse.User, RecyclerView.ViewHolder>(UserDiff) {

    interface OnItemClick {
        fun onUserClick(id:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    inner class UserViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserListResponse.User) {
            binding.apply {
                name.text = user.firstName
                photoImg.load(user.image)

                root.setOnClickListener {
                    listener.onUserClick(user.id.toString())
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(getItem(position))
    }
}

object UserDiff : DiffUtil.ItemCallback<UserListResponse.User>() {
    override fun areItemsTheSame(
        oldItem: UserListResponse.User,
        newItem: UserListResponse.User
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UserListResponse.User,
        newItem: UserListResponse.User
    ): Boolean {
        return oldItem == newItem
    }

}
