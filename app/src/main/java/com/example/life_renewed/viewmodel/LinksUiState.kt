package com.example.life_renewed.viewmodel

import com.example.life_renewed.model.LinksModel

sealed class LinksUiState {

    object Idle : LinksUiState()
    object Loading : LinksUiState()
    data class Success(val links: List<LinksModel>) : LinksUiState()
    data class Error(val message: String) : LinksUiState()
}