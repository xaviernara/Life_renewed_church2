package com.example.life_renewed.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.life_renewed.model.AnnouncementsItem
import com.example.life_renewed.model.NotesObject
import com.example.life_renewed.repo.LifeRenewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LifeRenewViewModel @Inject constructor(private val lifeRenewRepository: LifeRenewRepository) : ViewModel() {

    private var _announcements : MutableStateFlow<List<AnnouncementsItem>> = MutableStateFlow(emptyList())
    val announcements : StateFlow<List<AnnouncementsItem>> = _announcements

    private var _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isLoading : StateFlow<Boolean> = _isLoading

    private var _error : MutableStateFlow<String> = MutableStateFlow("")
    var error : StateFlow<String> = _error

    private var _allNotes : MutableStateFlow<List<NotesObject>> = MutableStateFlow(emptyList())
    var allNotes : StateFlow<List<NotesObject>> = _allNotes

    @RequiresApi(Build.VERSION_CODES.O)
    private var _note : MutableStateFlow<NotesObject> = MutableStateFlow(NotesObject())
    @RequiresApi(Build.VERSION_CODES.O)
    var note : StateFlow<NotesObject> = _note

    init {
        getAllNotes()
    }


    private fun getAllNotes() {
        _isLoading.value = true
        _error.value = ""
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    lifeRenewRepository.getNotesFromDB().collectLatest{
                        _allNotes.value = it
                    }
                }
            }
        } catch (e: Exception) {
            _error.value = e.message.toString()
        }finally {
            _isLoading.value = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNotesById(id : Int) {
        _isLoading.value = true
        _error.value = ""
        try {

            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    lifeRenewRepository.getNotesById(id).collectLatest{
                        _note.value = it
                    }
                }
            }
//            coroutineScope {
//                withContext(Dispatchers.IO) {
//                    lifeRenewRepository.getNotesById(id).collectLatest{
//                        _note.value = it
//                    }
//                }
//            }
        } catch (e: Exception) {
            _error.value = e.message.toString()
        }finally {
            _isLoading.value = false
        }
    }

    fun insertNotes(notesObject: NotesObject) {
        _isLoading.value = true
        _error.value = ""
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    lifeRenewRepository.insertNotesIntoDB(notesObject)
                }
            }
        } catch (e: Exception) {
            _error.value = e.message.toString()
        }
        finally {
            _isLoading.value = false
        }
    }

    fun deleteNotes(notesObject: NotesObject) {
        _isLoading.value = true
        _error.value = ""
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    lifeRenewRepository.deleteNotesFromDB(notesObject)
                }
            }
        } catch (e: Exception) {
            _error.value = e.message.toString()
        }
        finally {
            _isLoading.value = false
        }
    }




}