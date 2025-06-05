package com.example.life_renewed.repo

import com.example.life_renewed.model.AnnouncementsItem

sealed class ApiDataState()  {
    class Success(val nnouncementsItems: List<AnnouncementsItem>) : ApiDataState()
    object Loading : ApiDataState()
    class Error(val message: String) : ApiDataState()
}