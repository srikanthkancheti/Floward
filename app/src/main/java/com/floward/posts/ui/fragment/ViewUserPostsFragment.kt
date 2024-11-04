package com.floward.posts.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.floward.posts.R
import com.floward.posts.databinding.ViewUsersPostsFragmentBinding
import com.floward.posts.domain.model.UserModel
import com.floward.posts.ui.adapter.UserPostsListAdapter
import com.floward.posts.ui.utils.USER_ID
import com.floward.posts.ui.viewmodel.SharedFlowrdViewModel
import com.floward.posts.ui.viewmodel.ViewUserPostsViewModel
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ViewUserPostsFragment: Fragment() {

    private val viewModel: ViewUserPostsViewModel by viewModel {
        parametersOf(
            getSharedViewModel<SharedFlowrdViewModel>()
        )
    }
    private var userId: String = ""
    private lateinit var binding: ViewUsersPostsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getString(USER_ID) ?: ""
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.view_users_posts_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        getFlowardUserAndPosts()
        setUpUserImage()
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setUpUserImage() {
        Glide.with(this)
            .load(viewModel.shared.getUserUrl(userId))
            .into(binding.userImageView)
    }
    private fun getFlowardUserAndPosts() {
        if (viewModel.isPostsNotEmpty(userId))
            setUpUserPostsRecyclerView(viewModel.getUserPostsData(userId))
    }

    private fun setUpUserPostsRecyclerView(postsList: List<UserModel>) {
        binding.userPostsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.userPostsRecyclerView.adapter =
            UserPostsListAdapter(requireContext(), postsList)
    }
}