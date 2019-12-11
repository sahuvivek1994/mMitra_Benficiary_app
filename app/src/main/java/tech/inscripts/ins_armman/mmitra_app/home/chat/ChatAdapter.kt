package tech.inscripts.ins_armman.mmitra_app.home.chat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.home.HomeFragment
import tech.inscripts.ins_armman.mmitra_app.home.MainActivity

class ChatAdapter() : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {

    lateinit var context : Context
    var arrayOfNames = arrayOf("mark tuan","jackoson wang","aron kwak","bambam")
    var clickListener:ClickListener? = null
    var strName : String = ""

    constructor(mContext : Context?): this(){
        this.context = mContext!!

    }


    public interface ClickListener {
        fun itemClicked(v: View, position: Int)
    }

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
            var inflatedView = LayoutInflater.from(context).inflate(R.layout.itemview_layout,parent,false)
            return ChatHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return arrayOfNames.size
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.bindData(position)
    }

    inner class ChatHolder (v: View) : RecyclerView.ViewHolder(v),View.OnClickListener {
        var txtName : TextView = v.findViewById(R.id.txtName)
        init {
            v.setOnClickListener(this)
        }
        fun bindData(position: Int) {
           var i = arrayOfNames.size
            if (arrayOfNames != null) {
                strName = arrayOfNames[position]
                    txtName.text = strName

            }
        }

        override fun onClick(v: View) {
            val intent = Intent(context, ChatActivity::class.java)

            if (clickListener != null) {
                clickListener?.itemClicked(v, position)
                intent.putExtra("name", strName)
            }
            context?.startActivity(intent)
        }
    }

}