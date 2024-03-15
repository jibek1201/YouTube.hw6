package com.example.youtube.ui.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.youtube.data.model.BaseResponse
import com.example.youtube.databinding.ItemPlaylistBinding

class PlaylistsAdapter(private val onClick:(BaseResponse.Item)->Unit):ListAdapter<BaseResponse.Item, PlaylistsViewHolder>(
    YouTubeDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick)
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlaylistsViewHolder(
    private val binding: ItemPlaylistBinding,
    private val onClick: (BaseResponse.Item) -> Unit
):ViewHolder(binding.root){
    fun bind(item: BaseResponse.Item) = with(binding){
        playlistName.text = item.snippet.title
        tvCount.text = item.contentDetails.itemCount.toString().plus(" video series")
        itemView.setOnClickListener { onClick.invoke(item) }
        Glide.with(image).load(item.snippet.thumbnails.default.url).into(image)
    }

}

class YouTubeDiffCallback:DiffUtil.ItemCallback<BaseResponse.Item>(){
    override fun areItemsTheSame(oldItem: BaseResponse.Item, newItem: BaseResponse.Item) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BaseResponse.Item, newItem: BaseResponse.Item) = oldItem == newItem
}