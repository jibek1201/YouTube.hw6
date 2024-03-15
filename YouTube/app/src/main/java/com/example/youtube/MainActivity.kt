package com.example.youtube

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.bumptech.glide.load.engine.Resource
import com.example.youtube.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding


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

    private fun onClick {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("count", item.contentDetails.itemCount)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("description", item.snippet.description)
        startActivity(intent)
    }
}