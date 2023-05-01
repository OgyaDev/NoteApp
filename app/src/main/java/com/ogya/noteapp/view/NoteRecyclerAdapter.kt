package com.ogya.noteapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ogya.noteapp.R
import com.ogya.noteapp.data.NoteInfo

class NoteRecyclerAdapter(private val context: Context, private val notes: List<NoteInfo>) :
    RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_note_list, parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.itemNoteTextCourseTitle?.text = note.course?.title
        holder.itemNoteTextNoteTitle?.text = note.title
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        val itemNoteTextCourseTitle = itemView?.findViewById<TextView>(R.id.itemNoteTextCourseTitle)
        val itemNoteTextNoteTitle = itemView?.findViewById<TextView>(R.id.itemNoteTextNoteTitle)
    }
}