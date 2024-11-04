package com.floward.posts.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.floward.posts.R
import com.floward.posts.databinding.UserPostsListItemBinding
import com.floward.posts.domain.model.UserModel

class UserPostsListAdapter(
    private val context: Context,
    private val postsList: List<UserModel>,
) : RecyclerView.Adapter<UserPostsListAdapter.UserPostViewHolder>() {

    override fun getItemCount(): Int {
        return postsList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        val binding = DataBindingUtil.inflate<UserPostsListItemBinding>(
            LayoutInflater.from(context),
            R.layout.user_posts_list_item,
            parent,
            false
        )
        return UserPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        val userModel = postsList[position]
        holder.bind(userModel)
        holder.binding.postTitle.text = userModel.title
        holder.binding.postBody.text = userModel.body
    }

    class UserPostViewHolder(val binding: UserPostsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel) {
            binding.postData = userModel
            binding.executePendingBindings()
        }
    }
}