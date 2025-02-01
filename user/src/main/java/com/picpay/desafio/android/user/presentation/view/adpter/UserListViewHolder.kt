package com.picpay.desafio.android.user.presentation.view.adpter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.core.extensions.bindImageWithListener
import com.picpay.desafio.android.core.extensions.gone
import com.picpay.desafio.android.user.databinding.ListItemUserBinding
import com.picpay.desafio.android.user.presentation.model.UserModel

abstract class UserListBaseViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(user: UserModel)
}

class UserViewHolder(
    private val binding: ListItemUserBinding
) : UserListBaseViewHolder(binding.root) {
    override fun bind(user: UserModel) {
        binding.tvName.text = user.name
        binding.tvUsername.text = user.username
        binding.ivCircleAvatar.bindImageWithListener(
            binding.root.context,
            user.img,
            com.picpay.desafio.android.core.R.drawable.ic_round_account_circle,
            onResourceReady = {
                binding.progressUserAvatar.gone()
            },
            onLoadFailed =  {
                binding.progressUserAvatar.gone()
                return@bindImageWithListener
            }
        )
    }
}

