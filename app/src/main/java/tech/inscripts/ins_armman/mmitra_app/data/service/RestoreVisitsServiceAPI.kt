package tech.inscripts.ins_armman.mmitra_app.data.service

import com.example.mmitraprogramteam.data.Url.Companion.GET_VISITS
import tech.inscripts.ins_armman.mmitra_app.data.model.restoreData.RestoreDataRequest
import tech.inscripts.ins_armman.mmitra_app.data.model.restoreData.RestoreVisits
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestoreVisitsServiceAPI {
    @Headers("Content-Type: application/json")
    @POST(GET_VISITS)
     fun restoreRegistrationData(@Body request: RestoreDataRequest): Call<RestoreVisits>
}