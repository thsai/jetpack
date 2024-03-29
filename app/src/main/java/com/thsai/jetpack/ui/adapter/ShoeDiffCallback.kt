package com.thsai.jetpack.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.thsai.jetpack.db.data.Shoe

class ShoeDiffCallback : DiffUtil.ItemCallback<Shoe>() {
    override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem == newItem
    }

}