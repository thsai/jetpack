package com.thsai.jetpack.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thsai.jetpack.databinding.ShoeRecyclerItemBinding
import com.thsai.jetpack.db.data.Shoe

class ShoeAdapter constructor(val context: Context) :
    PagedListAdapter<Shoe, ShoeAdapter.ViewHolder>(ShoeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShoeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.apply {
            bind(onCreateListener(shoe!!.id),shoe)
        }
    }

    private fun onCreateListener(id: Long): View.OnClickListener {
        return View.OnClickListener {
            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(private val binding: ShoeRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: Shoe) {
            binding.apply {
                this.listener = listener
                this.shoe = item
                executePendingBindings()
            }
        }
    }


}