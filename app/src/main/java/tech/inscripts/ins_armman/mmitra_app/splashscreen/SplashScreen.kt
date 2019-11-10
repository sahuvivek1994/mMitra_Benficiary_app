package tech.inscripts.ins_armman.mmitra_app.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.login.Login
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        //declare animation
        val anim = AnimationUtils.loadAnimation(this,
            R.anim.splash_screen_bounce_efffect
        )
        val imageView : ImageView = findViewById(R.id.logoImage)

      //set animation
          imageView.startAnimation(anim)
        Timer().schedule(1000)
        {
            startActivity(Intent(this@SplashScreen, Login::class.java))
        }
    }
}