package tech.inscripts.ins_armman.mmitra_app.data.model.syncing

import com.google.gson.annotations.SerializedName
import tech.inscripts.ins_armman.mmitra_app.data.model.UserDetails

class RequestHelpModel : UserDetails() {
    @SerializedName("hash")
    private var hash: String? = null

    fun setHash(hash: String) {
        this.hash = hash
    }
}