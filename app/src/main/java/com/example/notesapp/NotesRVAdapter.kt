package com.example.notesapp

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class NotesRVAdapter(private val context:Context, private val listener: MainActivity):RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {
     private val allNotes = ArrayList<Notes>()

     inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val textview = itemView.findViewById<TextView>(R.id.text)
         val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
         val viewHolder =
             NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.items, parent, false))
         viewHolder.deleteButton.setOnClickListener {
             listener.onItemClicked(allNotes[viewHolder.adapterPosition])
         }
         return viewHolder
     }

     override fun getItemCount(): Int {
         return allNotes.size
     }

     override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
         val currentNotes = allNotes[position]
         holder.textview.text = currentNotes.text
     }
     fun updateList(newList:List<Notes>){
         allNotes.clear()
            allNotes.addAll(newList)
         notifyDataSetChanged()
     }
 }


     interface INotesRVADdapter{
         fun onItemClicked(notes: Notes)

     }



