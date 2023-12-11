package com.alanturing.cpifp.todo.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.alanturing.cpifp.todo.R

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
    }
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