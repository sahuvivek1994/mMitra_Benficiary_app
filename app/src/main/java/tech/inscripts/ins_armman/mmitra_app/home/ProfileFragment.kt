package tech.inscripts.ins_armman.mmitra_app.home



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import tech.inscripts.ins_armman.mmitra_app.R


class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* button_home.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.next_action)
        }*/

       /* arguments?.let {
            val safeArgs = PhotosFragmentArgs.fromBundle(it)
            textView_num.text = "Number of photos: ${safeArgs.numOfPhotos}"
        }*/
    }

}
