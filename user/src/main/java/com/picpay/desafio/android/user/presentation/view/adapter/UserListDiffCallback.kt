package com.picpay.desafio.android.user.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.user.presentation.model.UserModel

class UserListDiffCallback(
    private val oldList: List<UserModel>,
    private val newList: List<UserModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}