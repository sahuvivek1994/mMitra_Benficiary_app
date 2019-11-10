package com.example.mmitraprogramteam.data.service

import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class SyncUpdatePhotoService {
    var mPhotoServiceApi: SyncUpdatePhotoServiceApi?=null

    constructor(mPhotoServiceApi: SyncUpdatePhotoServiceApi?) {
        this.mPhotoServiceApi = mPhotoServiceApi
    }

/*
    fun syncUpdatePhotoDetails(updateImageModel: UpdateImageModel, updatedPhotoSync: IMainActivityInteractor.OnUpdatedPhotoSync){
        Log.d("SyncUpdatePhotoService", "SyncUpdatePhotoService start request")
        val responseBodyCall = mPhotoServiceApi!!.SyncUpdatePhotoDetails(updateImageModel)
        responseBodyCall.enqueue(object:retrofit2.Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                updatedPhotoSync.onFailureUpdatedPhotoSync(R.string.oops_some_thing_happened_wrong)

            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                var jsonResponse: String? = null
                try {
                    if (response!!.body() != null) {
                        jsonResponse = response.body().string()
                    } else if (response.errorBody() != null)
                        jsonResponse = response.errorBody().string()
                    val jsonObject = JSONObject(jsonResponse)
                    Log.d("SyncUpdatePhotoService", "SyncUpdatePhotoService end request")
                    updatedPhotoSync.onSuccessfullyUpdatedPhotoSync(jsonObject)
                } catch (e: IOException) {
                    e.printStackTrace()
                    updatedPhotoSync.onFailureUpdatedPhotoSync(R.string.input_output_error_occured)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    updatedPhotoSync.onFailureUpdatedPhotoSync(R.string.invalid_data_frm_server)
                }

            }

        })
    }

*/
}