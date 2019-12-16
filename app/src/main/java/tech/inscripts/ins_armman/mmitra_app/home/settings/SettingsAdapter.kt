package tech.inscripts.ins_armman.mmitra_app.home.settings

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SettingsAdapter(fm : FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        when (position){
            0->
                return FragmentPersonal()
            1->
                return FragmentMedical()
        else ->
            return FragmentMedical()
        }

    }

    override fun getCount(): Int { return 2 }
}