package tech.inscripts.ins_armman.mmitra_app.data.service

import com.example.mmitraprogramteam.data.Url.Companion.AUTHENTICATE
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import tech.inscripts.ins_armman.mmitra_app.data.model.UserDetails


interface LoginServiceAPI {
    @Headers("Content-Type: application/json")
    @POST(AUTHENTICATE)
    fun getAuthentication(@Body userDetails: UserDetails): Call<ResponseBody>
}