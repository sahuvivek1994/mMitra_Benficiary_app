package tech.inscripts.ins_armman.mmitra_app.login

import tech.inscripts.ins_armman.mmitra_app.utility.IBasePresenter

interface ILoginPresenter<v>
    : IBasePresenter<v>
{

    fun initializeDBHelper()
    fun checkPermissions(): Boolean
    fun getPermissions(listPermissionsNeeded: List<String>)
    fun validateCredentials(mobile: String)
    fun loginUser(username: String)
    fun createRequestBody(username: String)
    fun checkIfUserAlreadyLoggedIn()
}