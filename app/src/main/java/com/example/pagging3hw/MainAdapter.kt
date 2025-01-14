package com.example.pagging3hw

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagging3hw.RetrofitHelper.FilmsModels.Doc
import com.example.pagging3hw.databinding.ListItemBinding
import javax.inject.Inject

class MainAdapter @Inject constructor() :
    PagingDataAdapter<Doc, MainAdapter.MainViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Doc>() {

            override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.binding.apply {
            textViewName.text = item?.name
            textViewRating.text = item?.rating?.kp.toString()
            textViewYear.text = item?.year.toString()
        }
    }


    class MainViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}