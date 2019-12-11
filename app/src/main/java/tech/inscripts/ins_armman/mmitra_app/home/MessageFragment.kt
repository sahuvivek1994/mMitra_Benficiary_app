package tech.inscripts.ins_armman.mmitra_app.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.home.chat.ChatAdapter


class MessageFragment : Fragment(),ChatAdapter.ClickListener {

    var recyclerView : RecyclerView? =null
    lateinit var adapter : ChatAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_chatlist, container, false)
        recyclerView = view.findViewById(R.id.recyclerChatContacts)
        var layoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = layoutManager
        activity?.title = "Messages"
        adapter = ChatAdapter(activity)
        recyclerView?.adapter = adapter
        adapter.setOnClickListener(this)
        return view
    }

    /** This method allows to click on item from recyclerView **/
    override fun itemClicked(v: View, position: Int) {}
}
