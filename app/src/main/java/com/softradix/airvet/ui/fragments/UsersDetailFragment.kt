package com.softradix.airvet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softradix.airvet.base.BaseFragment
import com.softradix.airvet.databinding.FragmentUsersDetailBinding
import com.softradix.airvet.model.ResultsItem
import com.softradix.airvet.utils.Constants
import com.softradix.airvet.utils.loadImageWithoutShimmer


class UsersDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentUsersDetailBinding
    private var userDetails: ResultsItem? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUsersDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //getting the data through bundle
        arguments?.apply {
            userDetails = (getSerializable(Constants.USER_DETAILS) ?: "") as ResultsItem?
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() { //setting data
        if (userDetails != null) {
            binding.tvName.text =
                "${userDetails?.name?.title} ${userDetails?.name?.first} ${userDetails?.name?.last}"

            binding.tvEmail.text = userDetails?.email
            binding.tvPhone.text = userDetails?.phone
            binding.tvLocation.text =
                "${userDetails?.location?.state} , ${userDetails?.location?.country}"
            binding.tvDob.text = "Age: "+userDetails?.dob?.age.toString()

            binding.userImg.loadImageWithoutShimmer(
                userDetails?.picture?.medium ?: "",
            )
        }

        binding.backArrow.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}