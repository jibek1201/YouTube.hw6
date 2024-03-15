package com.example.youtube.ui.playlist_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.youtube.data.model.PlaylistItemsModel
import com.example.youtube.databinding.ItemDetailBinding

class PlaylistItemsAdapter:ListAdapter<PlaylistItemsModel.Item, PlaylistItemsViewHolder>(
    PlaylistItemsDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemsViewHolder {
        return PlaylistItemsViewHolder(ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistItemsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlaylistItemsViewHolder(private val binding:ItemDetailBinding):ViewHolder(binding.root){
    fun bind(item: PlaylistItemsModel.Item?) = with(binding) {
        tvTitle.text = item?.snippet?.title
        Glide.with(image).load(item?.snippet?.thumbnails?.default?.url).into(image)
    }
}

class PlaylistItemsDiffCallback:DiffUtil.ItemCallback<PlaylistItemsModel.Item>(){
    override fun areItemsTheSame(oldItem: PlaylistItemsModel.Item, newItem: PlaylistItemsModel.Item) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PlaylistItemsModel.Item, newItem: PlaylistItemsModel.Item) = oldItem == newItem

}