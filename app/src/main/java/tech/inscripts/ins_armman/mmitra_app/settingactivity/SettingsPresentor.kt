package tech.inscripts.ins_armman.mmitra_app.settingactivity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.database.Cursor
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.data.database.DatabaseContract
import tech.inscripts.ins_armman.mmitra_app.data.model.RequestFormModel
import tech.inscripts.ins_armman.mmitra_app.data.model.UpdateModel
import tech.inscripts.ins_armman.mmitra_app.data.model.restoreData.BeneficiariesList
import tech.inscripts.ins_armman.mmitra_app.data.model.restoreData.RestoreDataRequest
import tech.inscripts.ins_armman.mmitra_app.data.model.restoreData.RestoreRegistration
import tech.inscripts.ins_armman.mmitra_app.data.model.restoreData.RestoreVisits
import tech.inscripts.ins_armman.mmitra_app.data.model.syncing.BeneficiaryDetails
import tech.inscripts.ins_armman.mmitra_app.login.Login
import tech.inscripts.ins_armman.mmitra_app.utility.Constants.FORM_DOWNLOAD_LIMIT
import tech.inscripts.ins_armman.mmitra_app.utility.Utility
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class SettingsPresentor : ISettingsPresentor<ISettingsView>,
    ISettingsInteractor.OnFormDownloadFinished,
    ISettingsInteractor.onCheckUpdateFinished
    , ISettingsInteractor.OnRegistrationsDownloadFinished
    , ISettingsInteractor.OnVisitsDownloadFinished {

val utility= Utility()
    private val FETCH_USER_DATA = 101
    private val FETCH_FORM_HASH = 102

    private  var mSettingsView: ISettingsView? = null
    private var mSettingsInteractor: SettingsInteractor? = null
    private var mEmail: String = ""
    private var mPassword:String = ""
    private var mFormHash:String = ""

    private var totalPagesCalculated: Boolean = false

    private var totalPages: Int = 0
    private var pageCounter: Int = 0

    private lateinit var mRequest: RestoreDataRequest
    private val listRegistrations = ArrayList<BeneficiaryDetails>()
    private val listVisits = ArrayList<BeneficiariesList>()



    var onQueryFinished : ISettingsPresentor.OnQueryFinished = object :
        ISettingsPresentor.OnQueryFinished {
        override fun onSuccess(cursor: Cursor, id: Int) {
           when(id){
               FETCH_USER_DATA -> if (cursor.moveToFirst()){
                   mEmail=cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_USERNAME))
                   mPassword=cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_PASSWORD))
               }
               FETCH_FORM_HASH ->if (cursor.moveToFirst()){
                   mFormHash=cursor.getString(cursor.getColumnIndex(DatabaseContract.HashTable.COLUMN_HASH))
               }
           }

        }

        override fun onSuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onFailure() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }


    override fun changeLanguage(context: Context, language: String) {
    mSettingsInteractor?.changeLocale(context,language)
    }

    override fun downloadForms() {
        val a= mSettingsView?.getContext()
        val b= utility.hasInternetConnectivity(a)
        if (b) {
            mSettingsView?.showProgressBar(mSettingsView?.getContext()?.getString(R.string.downloading_data)!!)
            val details = RequestFormModel()
/*
            details.setusername(mEmail)
            details.setpassword(mPassword)
          // details.setImei("869432026925037")
            details.setImei(utility.getDeviceImeiNumber(mSettingsView!!.getContext()))
            details.setHash(mSettingsInteractor!!.getHash(HASH_ITEM_FORM))
            details.setShowdata("true")
*/

            details.setusername("deepalishelke")
            details.setpassword("deepalishelke")
             details.setImei("865596031245799")
          //  details.setImei(utility.getDeviceImeiNumber(mSettingsView!!.getContext()))
           // details.setHash(mSettingsInteractor!!.getHash(HASH_ITEM_FORM))
                 // details.setShowdata("true")


            mSettingsInteractor?.downloadForms(details, this)
        } else
            mSettingsView?.showSnackBar(mSettingsView?.getContext()?.getString(R.string.no_internet_connection)!!)
    }

    override fun checkUpdate() {
        if(utility.hasInternetConnectivity(mSettingsView?.getContext())){
            mSettingsView?.showProgressBar(mSettingsView!!.getContext().getString(R.string.looking_for_update))
            mSettingsInteractor?.checkReleaseUpdate(this)
        }else mSettingsView?.showSnackBar(mSettingsView!!.getContext().getString(R.string.no_internet_connection))
    }

    override fun downloadApk(apkLink: String) {
        mSettingsView?.showApkDownloadProgress()
        mSettingsInteractor?.downloadAndSaveApk(apkLink)
    }

    override fun setApkDownloadProgress(progress: Int) {
        mSettingsView?.updateApkDownloadProgress(progress)
    }

    override fun onApkDownloaded() {
      mSettingsView?.dissmissApkDownloadProgress()
    }

    override fun logout() {
        mSettingsInteractor?.deleteLoginDetails()
        val intent = Intent(mSettingsView?.getContext(), Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        mSettingsView?.getContext()?.startActivity(intent)
    }

    override fun restoreData() {
        if(utility.hasInternetConnectivity(mSettingsView?.getContext())) {
            mSettingsView?.showProgressBar(mSettingsView!!.getContext().getString(R.string.downloading_data))
            resetDataMemberValues()
            restoreRegistrations(pageCounter)
        }
        else mSettingsView?.showSnackBar(mSettingsView!!.getContext().getString(R.string.no_internet_connection))
    }

    override fun restoreRegistrations(pageNumber: Int) {
        mRequest?.setPageNumber(pageNumber)
        mSettingsInteractor?.downloadRegistrationData(mRequest, this)
    }

    override fun restoreVisits(pageNumber: Int) {
        mRequest.setPageNumber(pageNumber)
        mSettingsInteractor?.downloadVisitsData(mRequest,this)
    }

    override fun resetDataMemberValues() {
        mRequest = RestoreDataRequest()
        mRequest.email= mEmail!!
        mRequest.password= mPassword!!
        mRequest.setImei(utility.getDeviceImeiNumber(mSettingsView!!.getContext()))
        //mRequest.setImei("869432026925037")
        mRequest.setLimit(FORM_DOWNLOAD_LIMIT)

        pageCounter=1
        totalPages=0
        totalPagesCalculated=false
        listRegistrations.clear()
        listVisits.clear()
    }

    override fun attachView(view: ISettingsView) {
        mSettingsView=view
        mSettingsInteractor= SettingsInteractor(mSettingsView!!.getContext(),onQueryFinished,this)
        mSettingsInteractor?.fetchLoginDetails(FETCH_USER_DATA)
        mSettingsInteractor?.fetchFormJsonHash(FETCH_FORM_HASH)
    }
    override fun detachView() {
    }

    override fun onSuccessFormDownloading(jsonObject: JSONObject, hash: String) {
        mSettingsView?.hideProgressBar()
        var value : Boolean= false
        var a=mSettingsView?.getContext()
        try{
            value= jsonObject.get("status") as Boolean
        }
        catch(e : Exception ){

        }
        if(!value){
            if (jsonObject.has("response")) {
                mSettingsView?.showSnackBar(a!!.getString(R.string.forms_already_updated))
            } else {
                try {
                    jsonObject.put("hash", hash)
                    mSettingsInteractor?.saveFormData(jsonObject)
                    mSettingsView?.showSnackBar(mSettingsView?.getContext()?.getString(R.string.Toast_msg_for_formsavesuccessfully)!!)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    mSettingsView?.showSnackBar(a!!.getString(R.string.invalid_data_frm_server))
                }

            }
        }else{
            mSettingsView?.showSnackBar(a!!.getString(R.string.forms_already_updated))
        }

        /**
         * Code commented of hash check it will get uncomment for new version in which hash will be used on server side also
         */

        /*var value : Boolean =false

        value= jsonObject.get("status") as Boolean

        if(!value){
            if(jsonObject.has("response")){
                mSettingsView?.showSnackBar(mSettingsView!!.getContext().getString(R.string.forms_already_updated))
            } else{
                try{
                jsonObject.put("hash",hash)
                mSettingsInteractor?.saveFormData(jsonObject)
            }
            catch(e : JSONException){
                e.printStackTrace()
                mSettingsView?.showSnackBar(mSettingsView!!.getContext().getString(R.string.invalid_data_frm_server))
            }
            }
        }
        else{
            mSettingsView?.showSnackBar(mSettingsView!!.getContext().getString(R.string.forms_already_updated))
        }
*/
    }

    override fun onUpdateCheckSuccess(updateModel: UpdateModel) {
        mSettingsView?.hideProgressBar()
        if(!updateModel.getStatus()){
            mSettingsView?.showSnackBar(mSettingsView!!.getContext().getString(R.string.dialog_app_updated_text))
            return
        }
        val context: Context= mSettingsView?.getContext()!!
        try{
            val pInfo : PackageInfo= context.packageManager.getPackageInfo(context.packageName,0)
            var versionCode = pInfo.versionCode
            val newVersionCode = Integer.parseInt(updateModel.getData()!!.versionCode)

            if(newVersionCode>versionCode){
                mSettingsView!!.updateAvailable(updateModel!!.getData()!!.link)
            }
        }catch(e : PackageManager.NameNotFoundException){
            e.printStackTrace()
        }
    }


    override fun onSuccessRegistrationsDownloading(registration: RestoreRegistration) {
        if(registration.getTotal()>0){
           // registration.getRegistrationData()?.let { listRegistrations.addAll(it) }
            var a=registration.getRegistrationData()
            if (a != null) {
                listRegistrations.addAll(a)
            }
            if(!totalPagesCalculated){
                totalPagesCalculated= true
                totalPages = Math.ceil((registration.getTotal()).toDouble() / (FORM_DOWNLOAD_LIMIT).toDouble()).toInt()
            }
        }
        if(pageCounter<totalPages){
            restoreRegistrations(++pageCounter)
        }else{
            pageCounter=1
            totalPages=0
            totalPagesCalculated=false
            restoreVisits(pageCounter)
        }
    }


    override fun onSuccessVisitsDownloading(visits: RestoreVisits) {
        if(visits.getTotal()>0){
           // visits.getBeneficiariesLists()?.let { listVisits.addAll(it) }
            var a=visits.getBeneficiariesLists()
            if (a != null) {
                listVisits.addAll(a)
            }
            if(!totalPagesCalculated){
                totalPagesCalculated =true
                totalPages=Math.ceil((visits.getTotal()).toDouble() / (FORM_DOWNLOAD_LIMIT).toDouble() ).toInt()
            }
        }
        if (pageCounter < totalPages)  {
            restoreVisits(++pageCounter)
        }
        else {
            mSettingsView?.hideProgressBar()
            mSettingsInteractor?.saveDownloadedData(listRegistrations, listVisits)
            mSettingsView?.showSnackBar(mSettingsView?.getContext()!!.getString(R.string.msg_restore_successful))
        }
    }

    override fun onFailure(message: String) {
        mSettingsView!!.hideProgressBar()
        var errorString=""
        try{
            var errorObject= JSONObject(message)
            errorString=message
        }
        catch(e:JSONException){
            e.printStackTrace()
            errorString=message
        }
        mSettingsView!!.showSnackBar(errorString)
    }

}