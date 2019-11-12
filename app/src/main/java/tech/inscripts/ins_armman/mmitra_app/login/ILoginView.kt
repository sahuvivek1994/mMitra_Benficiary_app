package tech.inscripts.ins_armman.mmitra_app.login

import android.content.Context

interface ILoginView {

    fun setMobileError()

    fun resetErrorMsg()

    fun showDialog(title: String, message: String)

    fun showProgressBar()

    fun hideProgressBar()

    fun openHomeActivity()

    fun setAuthenticationFailedError()

    fun getContext(): Context

}