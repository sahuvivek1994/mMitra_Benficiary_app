package tech.inscripts.ins_armman.mmitra_app.home



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.settings_personal.*
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.home.settings.SettingsAdapter


class SettingsFragment : Fragment() {

    var tabLayout : TabLayout? =null
    var viewPager : ViewPager?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_settings, container, false)
        activity?.title = ""
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout?.addTab(tabLayout?.newTab()!!.setText("Personal"))
        tabLayout?.addTab(tabLayout?.newTab()!!.setText("Medical"))
        tabLayout?.addTab(tabLayout?.newTab()!!.setText(""))
        tabLayout?.addTab(tabLayout?.newTab()!!.setText(""))
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout?.setSelectedTabIndicatorColor(resources.getColor(R.color.start_pink))
        tabLayout?.setTabTextColors(resources.getColor(R.color.colorPrimary),resources.getColor(R.color.start_pink))
        var adapter = SettingsAdapter(fragmentManager)
        viewPager?.adapter = adapter
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout?.setupWithViewPager( this.viewPager)

         return view
    }

}
