package com.softradix.airvet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softradix.airvet.databinding.ItemUserListBinding
import com.softradix.airvet.model.ResultsItem
import com.softradix.airvet.utils.loadImageWithoutShimmer

class UsersAdapter(
    private val context: Context,
    private val list: ArrayList<ResultsItem?>,
    var onSelection: (resultsItem: ResultsItem?) -> Unit
) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private lateinit var binding: ItemUserListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            onSelection(list[position])
        }
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(binding: ItemUserListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(resultsItem: ResultsItem?) {
            binding.nameTv.text =
                "${resultsItem?.name?.title} ${resultsItem?.name?.first} ${resultsItem?.name?.last}"
            binding.emailTv.text = resultsItem?.email
            binding.phoneTv.text = resultsItem?.phone
            binding.userImg.loadImageWithoutShimmer(
                resultsItem?.picture?.medium ?: "",
            )
        }

    }
}