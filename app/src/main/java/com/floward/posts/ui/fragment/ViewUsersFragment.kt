package com.floward.posts.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.floward.posts.R
import com.floward.posts.api.EventObserver
import com.floward.posts.api.Result
import com.floward.posts.databinding.ViewUsersFragmentBinding
import com.floward.posts.domain.model.UserModel
import com.floward.posts.ui.adapter.ItemClickListener
import com.floward.posts.ui.adapter.UsersListAdapter
import com.floward.posts.ui.extensions.showToast
import com.floward.posts.ui.utils.USER_ID
import com.floward.posts.ui.viewmodel.SharedFlowrdViewModel
import com.floward.posts.ui.viewmodel.ViewUsersViewModel
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class ViewUsersFragment : Fragment(), ItemClickListener {

    private val viewModel: ViewUsersViewModel by viewModel {
        parametersOf(
            getSharedViewModel<SharedFlowrdViewModel>()
        )
    }

    private lateinit var binding: ViewUsersFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.view_users_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFlowardPosts()
    }

    private fun getFlowardPosts() {
        viewModel.getPostsTrigger.postValue(Unit)
        viewModel.getPostsResult.observe(viewLifecycleOwner, EventObserver { result ->
            when (result) {
                is Result.Success -> {
                    getFlowardUsers()
                }
                is Result.Error -> {
                    showPlaceHolderView()
                    Timber.e(result.exception.errorMessage.toString())
                    showToast(result.exception.errorMessage.toString())
                }
                Result.Loading -> {
                    showToast(getString(R.string.generic_loading_message))
                }
            }
        })
    }

    private fun getFlowardUsers() {
        viewModel.getUsersTrigger.postValue(Unit)
        viewModel.getUsersResult.observe(viewLifecycleOwner, EventObserver { result ->
            when (result) {
                is Result.Success -> {
                    if (viewModel.isUsersNotEmpty()) {
                        hidePlaceHolderView()
                        setUpUsersListRecyclerView(viewModel.getUsersList())
                        showToast(getString(R.string.generic_success_message))
                    }
                }
                is Result.Error -> {
                    showPlaceHolderView()
                    Timber.e(result.exception.errorMessage.toString())
                    showToast(result.exception.errorMessage.toString())
                }
                Result.Loading -> {
                }
            }
        })
    }

    private fun setUpUsersListRecyclerView(usersList: List<UserModel>?) {
        if (usersList.isNullOrEmpty()) {
            showPlaceHolderView()
            return
        }
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecyclerView.adapter =
            UsersListAdapter(requireContext(), usersList, this)
    }

    private fun showPlaceHolderView() {
        binding.flowardUsersView.visibility = View.GONE
        binding.errorMessageTextView.visibility = View.VISIBLE
    }

    private fun hidePlaceHolderView() {
        binding.flowardUsersView.visibility = View.VISIBLE
        binding.errorMessageTextView.visibility = View.GONE
    }

    override fun onItemClickListener(userModel: UserModel) {
        val bundle = Bundle().apply {
            putString(USER_ID, userModel.userId)
        }
        findNavController().navigate(R.id.action_viewUserPostsFragment, bundle)
    }
}
