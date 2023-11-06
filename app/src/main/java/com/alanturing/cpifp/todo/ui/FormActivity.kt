package com.alanturing.cpifp.todo.ui

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alanturing.cpifp.todo.R

abstract class FormActivity():AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.save ->  onSave()
        else -> false
    }
    abstract fun onSave():Boolean
}