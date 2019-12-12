package tech.inscripts.ins_armman.mmitra_app.home.chat

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import tech.inscripts.ins_armman.mmitra_app.R

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_messages)
        var name = intent.getStringExtra("name")
        var toolbar  = findViewById<androidx.appcompat.widget.Toolbar>(R.id.ActivityToolbar)
        toolbar.title = name
        setSupportActionBar(toolbar)
    }
}