package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) :AndroidViewModel(application) {
    val repository:NoteRepository
    val allNotes : LiveData<List<Notes>>
    init{
        val dao=NotesDb.getDatabase(application).getNotesDAO()
         repository=NoteRepository(dao)
        allNotes=repository.allNotes
    }
    fun deleteNote(notes: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }
    fun insertNote(notes: Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notes)
    }
}