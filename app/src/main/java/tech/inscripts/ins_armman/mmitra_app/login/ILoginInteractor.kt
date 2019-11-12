package tech.inscripts.ins_armman.mmitra_app.login

import android.content.Context
import org.json.JSONObject
import tech.inscripts.ins_armman.mmitra_app.data.model.UserDetails

interface ILoginInteractor {

    fun saveUserDetails(username: String, password: String, jsonObject: JSONObject)

    fun login(userDetails: UserDetails, onLoginFinished:OnLoginFinished, context:Context)

    fun deleteUserDetails()

    fun userAlreadyLoggedIn(): Boolean

    interface OnLoginFinished
    {
            fun onSuccess(jsonObject: JSONObject)

            fun onFailure(message:String)
    }

}