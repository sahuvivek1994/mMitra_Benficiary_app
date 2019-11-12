package tech.inscripts.ins_armman.mmitra_app.data.service

import android.content.Context
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.data.model.syncing.FormDetails
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


class SyncFormService
{
/*
    var mFormServiceApi : SyncFormServiceApi?=null

    constructor(mFormServiceApi: SyncFormServiceApi?) {
        this.mFormServiceApi = mFormServiceApi
    }

    fun syncForms(formDetails: FormDetails, onFormSync : IMainActivityInteractor.OnFormSync, context: Context){
       var responseBodyCall : Call<ResponseBody> = mFormServiceApi!!.syncFormDetails(formDetails)
        responseBodyCall.enqueue(object : retrofit2.Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                onFormSync.onFailure(context.getString(R.string.oops_some_thing_happened_wrong))

            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
          try{
              var loginJsonResponse : String?=null
              if(response!!.body()!=null){
                  loginJsonResponse = response.body()?.string()
              }
              else if(response!!.errorBody()!=null){
                  loginJsonResponse = response.errorBody()?.string()
              }
              val jsonObject = JSONObject(loginJsonResponse)
              onFormSync.onSuccessfullySyncForm(jsonObject)
          }
          catch(e : IOException){
              e.printStackTrace()
              onFormSync.onFailure(context.getString(R.string.input_output_error_occured))
          }
                catch(e: JSONException){
                    e.printStackTrace()
                    onFormSync.onFailure(context.getString(R.string.invalid_data_frm_server))
                }
            }

        })
    }
*/


}