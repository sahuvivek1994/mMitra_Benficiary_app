package tech.inscripts.ins_armman.mmitra_app.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.settings_personal.*
import tech.inscripts.ins_armman.mmitra_app.R

class FragmentMedical : Fragment() {override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    var view = inflater.inflate(R.layout.settings_personal, container, false)
    return view
}

}