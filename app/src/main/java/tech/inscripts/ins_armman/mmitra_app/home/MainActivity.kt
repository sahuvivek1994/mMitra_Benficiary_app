package tech.inscripts.ins_armman.mmitra_app.home
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.profile.ProfileActivity

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{

    lateinit var  toolbar : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        toolbar= findViewById(R.id.ActivityToolbar)
        setupBottomNavMenu(navController)
        toolbar?.setTitleTextColor(Color.WHITE)
        toolbar?.setNavigationIcon(R.drawable.ic_navigation_icon)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.nav_msg -> {
                startActivity(Intent(this, MessageFragment::class.java))
            }
            R.id.nav_settings -> {
                startActivity(Intent(this, SettingsFragment::class.java))

            }
            R.id.nav_share_us ->{}

            R.id.nav_logout -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.logout)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage(R.string.logout_message)
                    .setCancelable(false)
                    .setPositiveButton(
                        R.string.ok
                    ) { dialog, which -> //mPresenter?.logout()
                         }
                    .setNegativeButton(
                        R.string.cancel
                    ) { dialog, which -> dialog.cancel() }
                    .show()

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true    }
}
