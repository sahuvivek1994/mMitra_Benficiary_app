package tech.inscripts.ins_armman.mmitra_app.data.model

import com.google.gson.annotations.SerializedName

class RequestFormModel : UserDetails() {
    @SerializedName("hash")
    private var hash : String?=null
    fun setHash(hash : String){
        this.hash=hash
    }
}