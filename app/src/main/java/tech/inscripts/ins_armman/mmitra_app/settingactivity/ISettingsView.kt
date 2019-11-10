package tech.inscripts.ins_armman.mmitra_app.settingactivity

import tech.inscripts.ins_armman.mmitra_app.utility.IMvpView

interface ISettingsView  : IMvpView {
    fun showProgressBar(label :String)
    fun hideProgressBar()
    fun showDialog(title : String,message : String)
    fun updateAvailable(url : String)
    fun showApkDownloadProgress()
     fun updateApkDownloadProgress(progress: Int)
    fun dissmissApkDownloadProgress()
    fun showSnackBar(message : String)
}