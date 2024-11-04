package com.floward.posts.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.floward.posts.R
import com.floward.posts.databinding.UsersListItemBinding
import com.floward.posts.ui.extensions.setPhotoImageUrl
import com.floward.posts.domain.model.UserModel

class UsersListAdapter(
    private val context: Context,
    private val usersList: List<UserModel>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<UsersListAdapter.UsersViewHolder>() {

    override fun getItemCount(): Int {
        return usersList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = DataBindingUtil.inflate<UsersListItemBinding>(
            LayoutInflater.from(context),
            R.layout.users_list_item,
            parent,
            false
        )
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val userModel = usersList[position]
        holder.bind(userModel)
        holder.binding.userImageView.setPhotoImageUrl(
            holder.binding.userImageView.context,
            userModel.thumbnailUrl ?: "",
            R.drawable.ic_launcher_foreground
        )
        holder.binding.userName.text = userModel.name
        holder.binding.postsCount.text = userModel.getPostsCount()
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClickListener(userModel)
        }
    }

    class UsersViewHolder(val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel) {
            binding.userData = userModel
            binding.executePendingBindings()
        }
    }
}

interface ItemClickListener {
    fun onItemClickListener(userModel: UserModel)
}
