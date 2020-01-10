package tech.inscripts.ins_armman.mmitra_app.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.settings_personal.*
import tech.inscripts.ins_armman.mmitra_app.R

class FragmentMedical : Fragment() {
    var editPhone : EditText?=null
    var editEmail : EditText?=null
    var editAddr : EditText?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    var view = inflater.inflate(R.layout.settings_medical, container, false)

        editPhone = view.findViewById(R.id.editPhone)
        editEmail = view.findViewById(R.id.editEmail)
        editAddr = view.findViewById(R.id.editAddr)

        editPhone?.isEnabled = false
        editEmail?.isEnabled = false
        editAddr?.isEnabled = false
        return view
}

}