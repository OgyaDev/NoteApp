package com.ogya.noteapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ogya.noteapp.databinding.ActivityNoteListBinding
import com.ogya.noteapp.datamanager.DataManager

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
           val activityIntent = Intent(this,NoteActivity::class.java)
            startActivity(activityIntent)
        }

        binding.contentNoteList.listNoteItems.layoutManager = LinearLayoutManager(this)
        binding.contentNoteList.listNoteItems.adapter = NoteRecyclerAdapter(this, DataManager.notes)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.contentNoteList.listNoteItems.adapter?.notifyDataSetChanged()
    }
}