package com.example.life_renewed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.life_renewed.model.AnnouncementsItem
import com.example.life_renewed.repo.LifeRenewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LifeRenewViewModel @Inject constructor(private val lifeRenewRepository: LifeRenewRepository) : ViewModel() {

    private var _announcements : MutableStateFlow<List<AnnouncementsItem>> = MutableStateFlow(emptyList())
    val announcements : StateFlow<List<AnnouncementsItem>> = _announcements

    fun getAnnouncements() {
        viewModelScope.launch {
            lifeRenewRepository.getAnnouncementsFromDB().collectLatest{

            }

        }

    }




}