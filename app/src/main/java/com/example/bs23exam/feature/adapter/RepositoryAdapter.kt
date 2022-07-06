package com.example.bs23exam.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bs23exam.common.models.roomDB.RepositoryItem
import com.example.bs23exam.databinding.LayoutRepositoryItemBinding

class RepositoryAdapter constructor(val repoList: ArrayList<RepositoryItem>, val listener: ClickListener): RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {
    class ViewHolder (val binding: LayoutRepositoryItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutRepositoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singleItem = repoList[position]
        holder.binding.repo = singleItem
        holder.binding.executePendingBindings()
        holder.binding.parentLayout.setOnClickListener {
            singleItem.id?.let { listener.itemClicked(it) }
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    interface ClickListener {
        fun itemClicked(id: Int)
    }
}