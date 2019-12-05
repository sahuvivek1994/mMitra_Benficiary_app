package tech.inscripts.ins_armman.mmitra_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.inscripts.ins_armman.mmitra_app.login.ActivityLogin
import tech.inscripts.ins_armman.mmitra_app.login.Login
import java.util.*
import kotlin.concurrent.schedule

class Splash_scren : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Timer().schedule(2000)
        {
            startActivity(Intent(this@Splash_scren, ActivityLogin::class.java))
        }
    }
}
