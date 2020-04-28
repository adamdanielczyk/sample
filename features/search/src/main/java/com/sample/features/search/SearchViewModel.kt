package com.sample.features.search

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.sample.core.data.local.CharacterEntity
import com.sample.core.data.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn

class SearchViewModel(repository: CharacterRepository) : ViewModel() {

    val allCharacters: Flow<List<CharacterEntity>> = repository.getAllCharacters()
        .flowOn(Dispatchers.IO)

    private val _openDetailsScreen = BroadcastChannel<Pair<CharacterEntity, ImageView>>(1)
    val openDetailsScreen: Flow<Pair<CharacterEntity, ImageView>> = _openDetailsScreen.asFlow()

    fun onItemClicked(entity: CharacterEntity, imageView: ImageView) {
        _openDetailsScreen.offer(entity to imageView)
    }
}