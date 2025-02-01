package com.picpay.desafio.android.user.presentation.view.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.user.databinding.ListItemUserBinding
import com.picpay.desafio.android.user.presentation.model.UserModel

class UserListAdapter(
    private val userList: ArrayList<UserModel> = arrayListOf(),
):RecyclerView.Adapter<UserListBaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListBaseViewHolder {
       return  UserViewHolder(
           ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       )
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserListBaseViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun updateUserList(newUserList: List<UserModel>) {
        val diffCallback = DiffUtil.calculateDiff(UserListDiffCallback(userList, newUserList))

        userList.clear()
        userList.addAll(userList)

        diffCallback.dispatchUpdatesTo(this)
    }

}