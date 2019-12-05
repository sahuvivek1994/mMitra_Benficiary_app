package tech.inscripts.ins_armman.mmitra_app.settingactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.home.MainActivity

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setTitle(R.string.action_settings)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        toolbar?.setTitle(R.string.action_settings)
//        if (supportActionBar != null) {
//            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        }

        fragmentManager.beginTransaction().replace(R.id.frame_settings_menu, MainPreferenceFragment()).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
