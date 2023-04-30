package com.ogya.noteapp.view

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
import com.ogya.noteapp.IntentConstants
import com.ogya.noteapp.R
import com.ogya.noteapp.data.CourseInfo
import com.ogya.noteapp.databinding.ActivityMainBinding
import com.ogya.noteapp.datamanager.DataManager

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
        displayNote()
    }

    private fun displayNote() {
        notePosition = intent.getIntExtra(IntentConstants.EXTRA_NOTE_POSITION, POSITION_NOT_SET)
        if(notePosition != POSITION_NOT_SET){
            val note = DataManager.notes[notePosition]
            binding.contentMain.noteTitle.setText(note.title)
            binding.contentMain.noteText.setText(note.text)

            val coursePosition = DataManager.courses.values.indexOf(note.course)
            binding.contentMain.courseSpinner.setSelection(coursePosition)
        }
    }

    private fun spinnerPopulate() {
        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}