package com.example.youtube.di

import com.example.youtube.ui.playlist_items.PlaylistItemsViewModel
import com.example.youtube.ui.playlists.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        PlaylistsViewModel(get())
    }
    viewModel {
        PlaylistItemsViewModel(get())
    }
}