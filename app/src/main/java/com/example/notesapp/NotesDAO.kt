package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes){

    }
    @Delete
    suspend fun delete(notes: Notes){

    }
    @Query("Select * from notes_t order by id ASC")
    fun getAllNotes():LiveData<List<Notes>>

}
