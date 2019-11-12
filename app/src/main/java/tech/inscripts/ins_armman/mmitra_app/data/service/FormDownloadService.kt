package tech.inscripts.ins_armman.mmitra_app.data.service

import android.content.Context
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.data.model.RequestFormModel
import tech.inscripts.ins_armman.mmitra_app.settingactivity.ISettingsInteractor
import tech.inscripts.ins_armman.mmitra_app.utility.Utility
import java.io.IOException

class FormDownloadService {
   val utility = Utility()
    var formDownloadServiceAPI : FormDownloadServiceAPI?=null

    constructor(formDownloadServiceAPI: FormDownloadServiceAPI?) {
        this.formDownloadServiceAPI = formDownloadServiceAPI
    }

    fun downloadForms(requestFormModel: RequestFormModel, onFormDownloadFinished : ISettingsInteractor.OnFormDownloadFinished, context: Context){
        if(requestFormModel != null){
            val responseBodyCall : Call<ResponseBody> = formDownloadServiceAPI!!.downloadFormJson(requestFormModel)
            responseBodyCall.enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    onFormDownloadFinished.onFailure(context.getString(R.string.oops_some_thing_happened_wrong))
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    try{
                    var loginJsonResponse : String =""
                    if(response!!.body()!=null){
                        loginJsonResponse = response.body()!!.string()
                    }
                    else if(response.errorBody()!=null){
                        loginJsonResponse = response.errorBody()!!.string()
                    }
                    val loginJsonObject = JSONObject(loginJsonResponse)
                    onFormDownloadFinished.onSuccessFormDownloading(loginJsonObject,utility.mdFive(loginJsonResponse))
                    }
                    catch (e : IOException){
                        e.printStackTrace()
                        onFormDownloadFinished.onFailure(context.getString(R.string.input_output_error_occured))
                    }
                    catch (e: JSONException){
                        e.printStackTrace()
                        onFormDownloadFinished.onFailure(context.getString(R.string.input_output_error_occured))
                    }
                }
            })
        }
    }
}