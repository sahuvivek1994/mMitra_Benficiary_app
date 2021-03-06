package tech.inscripts.ins_armman.mmitra_app.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.login.ActivityLogin
import tech.inscripts.ins_armman.mmitra_app.login.Login
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        //declare animation
        val anim = AnimationUtils.loadAnimation(this, R.anim.slide_down_effect)
        val anim1 = AnimationUtils.loadAnimation(this, R.anim.slide_up_effect)

        var imgLogo = findViewById<ImageView>(R.id.logo)

      //set animation
        imgLogo.startAnimation(anim)
        Timer().schedule(1000)
        {
            startActivity(Intent(this@SplashScreen, ActivityLogin::class.java))
        }
    }
}