package com.softradix.airvet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.softradix.airvet.R
import com.softradix.airvet.adapter.UsersAdapter
import com.softradix.airvet.base.BaseFragment
import com.softradix.airvet.databinding.FragmentUsersListBinding
import com.softradix.airvet.model.ResultsItem
import com.softradix.airvet.utils.Constants
import com.softradix.airvet.utils.toast
import com.softradix.airvet.viewModel.UserViewModel

//@AndroidEntryPoint
class UsersListFragment : BaseFragment() {

    private lateinit var binding: FragmentUsersListBinding
    private lateinit var usersAdapter: UsersAdapter
    private val usersList = ArrayList<ResultsItem?>()
    private var isApiHit = false

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUsersListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachObservers()
    }

    private fun attachObservers() {
        //observing the view model data
        userViewModel.getUsersResponse.observe(this) { response ->
            response.results?.let {
                usersList.clear()
                usersList.addAll(it)
                usersAdapter.notifyDataSetChanged()

            }
        }

        userViewModel.apiError.observe(this) {
            it?.let {
                toast(it)
            }
        }

        userViewModel.onFailure.observe(this) {
            it?.let {
                toast(it.message)
            }
        }

        userViewModel.isLoading.observe(this) {
            it?.let {
                showLoading(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isApiHit) {
            userViewModel.getUsers()
            isApiHit = true
        }

        setUpRecyclerView()
        swipeRefresh()
    }

    private fun swipeRefresh() { //swipe refresh
        binding.swipeRefresh.setOnRefreshListener {
            userViewModel.getUsers()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setUpRecyclerView() { //setting up the recyclerview
        binding.usersListRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        usersAdapter = UsersAdapter(requireContext(), usersList) { resultsItem ->
            //sending UserDetail data
            callFragment(
                binding.root,
                R.id.action_usersListFragment_to_usersDetailFragment,
                Bundle().apply {
                    putSerializable(Constants.USER_DETAILS, resultsItem as ResultsItem)
                })
        }
        binding.usersListRecyclerview.adapter = usersAdapter
    }


}