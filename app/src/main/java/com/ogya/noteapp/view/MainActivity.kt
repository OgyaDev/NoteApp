package com.ogya.noteapp.view

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.ogya.noteapp.IntentConstants
import com.ogya.noteapp.R
import com.ogya.noteapp.data.CourseInfo
import com.ogya.noteapp.data.NoteInfo
import com.ogya.noteapp.databinding.ActivityMainBinding
import com.ogya.noteapp.datamanager.DataManager
import com.ogya.noteapp.datamanager.DataManager.getFirstIndex

const val POSITION_NOT_SET = -1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        spinnerPopulate()
        notePosition = intent.getIntExtra(IntentConstants.EXTRA_NOTE_POSITION, POSITION_NOT_SET)
        if (notePosition != POSITION_NOT_SET) {
            displayNote()
        }
        else{
            createNewNote()
        }
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun createNewNote() {
        DataManager.notes.add(NoteInfo())
        notePosition = DataManager.notes.lastIndex
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = binding.contentMain.noteTitle.text.toString()
        note.text = binding.contentMain.noteText.text.toString()
        note.course = binding.contentMain.courseSpinner.selectedItem as CourseInfo
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        binding.contentMain.noteTitle.setText(note.title)
        binding.contentMain.noteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.contentMain.courseSpinner.setSelection(coursePosition)

    }

    private fun spinnerPopulate() {
        val adapterCourses = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.contentMain.courseSpinner.adapter = adapterCourses
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.actionBack -> {
                notePosition--
                displayNote()
                invalidateOptionsMenu()
                true
            }
            R.id.actionNext -> {
                notePosition++
                displayNote()
                invalidateOptionsMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition >= DataManager.notes.lastIndex){
            val menuItem: MenuItem? = menu?.findItem(R.id.actionNext)
            menuItem?.icon = getDrawable(R.drawable.ic_baseline_block_white_24)
            menuItem?.isEnabled = false
        }
        else if(notePosition <= DataManager.notes.getFirstIndex()){
            val menuItem: MenuItem? = menu?.findItem(R.id.actionBack)
            menuItem?.icon = getDrawable(R.drawable.ic_baseline_block_white_24)
            menuItem?.isEnabled = false
        }
        return super.onPrepareOptionsMenu(menu)
    }


}