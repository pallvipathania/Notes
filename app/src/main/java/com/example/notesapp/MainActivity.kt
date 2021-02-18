package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

abstract class MainActivity : AppCompatActivity(),INotesRVADdapter {

     lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView .layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        recyclerView.adapter=adapter

    viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list->
            list?.let{
                adapter.updateList(it)
            }

        })

    }

    override fun onItemClicked(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this,"${notes.text} Deleted",Toast.LENGTH_LONG)

    }

    fun submitdata(view: View) {
        val noteText=input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Notes(noteText))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_LONG)
        }
    }

}