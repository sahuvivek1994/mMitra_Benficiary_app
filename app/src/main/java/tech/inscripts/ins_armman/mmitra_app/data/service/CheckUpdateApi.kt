package tech.inscripts.ins_armman.mmitra_app.data.service

import retrofit2.Call
import retrofit2.http.GET
import tech.inscripts.ins_armman.mmitra_app.data.model.UpdateModel

interface CheckUpdateApi {
   // @GET(RELEASE)
   fun getUpdateData(): Call<UpdateModel>
}

