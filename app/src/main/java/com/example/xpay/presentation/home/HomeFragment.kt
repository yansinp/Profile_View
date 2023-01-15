package com.example.xpay.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xpay.R
import com.example.xpay.databinding.FragmentHomeBinding
import com.example.xpay.domain.model.UserListResponse
import com.example.xpay.presentation.home.adapter.UsersListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), UsersListAdapter.OnItemClick {

    private var skip: Int = 0
    private var limit: Int = 20
    private lateinit var layoutManager: LinearLayoutManager
    private var usersList: MutableList<UserListResponse.User?> = mutableListOf()
    private var isLoading: Boolean = false

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val usersListAdapter by lazy { UsersListAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initRecycler()
        viewModel.getUsersList(limit.toString(), skip.toString())
        observeHomeState()
        handlePagination()
    }

    private fun initRecycler() {
        binding.apply {
            layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = usersListAdapter
            recycler.layoutManager = layoutManager
        }
    }

    private fun handlePagination() {
        binding.apply {
            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount: Int = layoutManager.childCount
                    val pastVisibleItem: Int =
                        layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = layoutManager.itemCount

                    if (dy > 0) {
                        if (!isLoading) {
                            if ((visibleItemCount + pastVisibleItem) >= total) {
                                if (limit <= 100 && skip <= 80) {
                                    skip += 20
                                    viewModel.getUsersList(limit.toString(), skip.toString())
                                }
                            }
                        }
                    }
                }
            })
        }
    }

    private fun observeHomeState() = viewLifecycleOwner.lifecycleScope.launch() {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.homeState.collectLatest {
                when (it) {
                    is HomeState.Loading -> handleLoading(showLoading = true)
                    is HomeState.SuccessUsersList -> handleSuccess(it.userList)
                    is HomeState.Error -> showError(it.error)
                    else -> Unit
                }
            }
        }
    }

    private fun showError(error: String) {
        handleLoading(showLoading = false)
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleSuccess(userList: UserListResponse) {
        handleLoading(showLoading = false)
        if (!usersList.containsAll(userList.users))
            usersList.addAll(userList.users)
        usersListAdapter.submitList(usersList)
        usersListAdapter.notifyDataSetChanged()
    }

    private fun handleLoading(showLoading: Boolean) = with(binding) {
        progressBar.isVisible = showLoading
        isLoading = showLoading
    }

    override fun onUserClick(id: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id))
    }
}