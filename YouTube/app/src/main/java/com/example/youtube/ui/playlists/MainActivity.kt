package com.example.youtube.ui.playlists

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.youtube.data.model.BaseResponse
import com.example.youtube.data.service.Resource
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.ui.base.BaseActivity
import com.example.youtube.ui.playlist_items.PlaylistItemsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PlaylistsViewModel by viewModel()
    private val adapter by lazy { PlaylistsAdapter(this::onClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPlaylists().stateHandler(
            success = {
                adapter.submitList(it)
                binding.recyclerView.adapter = adapter
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
    }

    private fun onClick(item: BaseResponse.Item) {
        val intent = Intent(this, PlaylistItemsActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("count", item.contentDetails.itemCount)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("description", item.snippet.description)
        startActivity(intent)
    }
}