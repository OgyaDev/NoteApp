package com.ogya.noteapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.ogya.noteapp.R
import com.ogya.noteapp.databinding.ActivityItemsBinding
import com.ogya.noteapp.datamanager.DataManager


class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityItemsBinding
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.appBarItems.toolbar
        drawerLayout = binding.drawerLayout
        navView = binding.navView

        setSupportActionBar(toolbar)

        binding.appBarItems.fab.setOnClickListener { view ->
            val activityIntent = Intent(this,NoteActivity::class.java)
            startActivity(activityIntent)
        }

        binding.appBarItems.listItemsInclude.listItems.layoutManager = LinearLayoutManager(this)
        binding.appBarItems.listItemsInclude.listItems.adapter = NoteRecyclerAdapter(this, DataManager.notes)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav_header_desc, R.string.close_nav_header_desc)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        binding.appBarItems.listItemsInclude.listItems.adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}