package com.example.mmitraprogramteam.data.service

import com.example.mmitraprogramteam.data.Url.Companion.SYNC_REGISTRATION_DATA
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import tech.inscripts.ins_armman.mmitra_app.data.model.SyncRegistrationDetails

interface SyncRegistrationServiceApi {
    @Headers("Content-Type: application/json")

    @POST(SYNC_REGISTRATION_DATA)

    fun SyncRegistrationDetails(@Body registrationDetails : SyncRegistrationDetails) : Call<ResponseBody>

}