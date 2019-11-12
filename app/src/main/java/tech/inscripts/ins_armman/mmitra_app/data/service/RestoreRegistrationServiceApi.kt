package tech.inscripts.ins_armman.mmitra_app.data.service

import com.example.mmitraprogramteam.data.Url.Companion.GET_REGISTRATIONS
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import tech.inscripts.ins_armman.mmitra_app.data.model.restoreData.RestoreDataRequest
import tech.inscripts.ins_armman.mmitra_app.data.model.restoreData.RestoreRegistration

interface RestoreRegistrationServiceApi {
    @Headers("Content-Type: application/json")
    @POST(GET_REGISTRATIONS)
     fun restoreRegistrationData(@Body request: RestoreDataRequest): Call<RestoreRegistration>
}