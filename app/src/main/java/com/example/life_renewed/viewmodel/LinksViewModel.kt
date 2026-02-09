package com.example.life_renewed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.life_renewed.R
import com.example.life_renewed.model.LinksModel
import com.example.life_renewed.viewmodel.LinksUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LinksViewModel : ViewModel() {

    private val _links = MutableLiveData<List<LinksModel>>()

    val links : LiveData<List<LinksModel>> = _links

//    private val _uiState2 = MutableStateFlow<LinksUiState> (LinksUiState.Idle)
//
//    val uiState2 = StateFlow<LinksUiState> = _uiState2.asStateFlow()

    private val _uiState: MutableStateFlow<LinksUiState> = MutableStateFlow(LinksUiState.Loading)

    val uiState: StateFlow<LinksUiState> = _uiState.asStateFlow()

    init{
        getLinkModels()
    }


    private fun getLinkModels(){
        _uiState.value = LinksUiState.Loading
        try {
            _links.value = listOf(
                LinksModel("Facebook", "user@example.com", R.drawable.ic_facebook_foreground),
                LinksModel("Twitter", "user@example.com", R.drawable.ic_x_twitter_foreground),
                LinksModel("Youtube", "user@example.com", R.drawable.ic_youtube_foreground),
                LinksModel("Facebook", "user@example.com", R.drawable.ic_facebook_foreground),
                LinksModel("Twitter", "user@example.com", R.drawable.ic_x_twitter_foreground),
                LinksModel("Youtube", "user@example.com", R.drawable.ic_youtube_foreground),
                LinksModel("Facebook", "user@example.com", R.drawable.ic_facebook_foreground),
                LinksModel("Twitter", "user@example.com", R.drawable.ic_x_twitter_foreground),
                LinksModel("Youtube", "user@example.com", R.drawable.ic_youtube_foreground),
                LinksModel("Facebook", "user@example.com", R.drawable.ic_facebook_foreground),
                LinksModel("Twitter", "user@example.com", R.drawable.ic_x_twitter_foreground),
                LinksModel("Youtube", "user@example.com", R.drawable.ic_youtube_foreground)
            )
            _uiState.value = LinksUiState.Success(_links.value ?: emptyList())
        }catch(e: Exception){
            _uiState.value = LinksUiState.Error(e.message ?: "Unknown Error")
        }

    }


}