package tech.inscripts.ins_armman.mmitra_app.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.progress_overlay.*
import tech.inscripts.ins_armman.mmitra_app.R
import tech.inscripts.ins_armman.mmitra_app.data.database.DBHelper
import tech.inscripts.ins_armman.mmitra_app.data.database.DatabaseManager
import tech.inscripts.ins_armman.mmitra_app.home.MainActivity
import tech.inscripts.ins_armman.mmitra_app.utility.Utility


class Login : AppCompatActivity(), ILoginView , View.OnClickListener {

    var permissionArrays =
        arrayOf<String>(Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE)
    var REQUEST_CODE = 101

    val mLoginPresenter = LoginPresenter()
    val uti = Utility()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            var applicationLanguage = uti.getLanguagePreferance(applicationContext)
            if(applicationLanguage?.isEmpty()!!)
            {
                uti.setApplicationLocale(applicationContext,"en")
            }
            else
            {
                uti.setApplicationLocale(applicationContext,applicationLanguage)
            }
            setContentView(R.layout.activity_login)
            mLoginPresenter.attachView(this)

        edit_mobile_number.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE)
                buttonLogin.performClick()
            false
        }

        buttonLogin.setOnClickListener {
            //var mobileNo = edittext_mobile.text.toString()
            /*var mobileNo = "7738621083"
            mLoginPresenter.validateCredentials(mobileNo)*/
            openHomeActivity()
        }

        edit_mobile_number.setOnClickListener(this)

    }

    fun initializeDBHelper() {
       /* Log.e("ABC", getContext().applicationContext.toString())
        println("ABC"+ getContext().applicationContext)*/
        val dbHelper = DBHelper(getContext())
        DatabaseManager.initializeInstance(dbHelper)
        DatabaseManager.getInstance().openDatabase()
    }


    override fun setMobileError() {
        inputMobileNo.setErrorTextColor(ColorStateList.valueOf(Color.WHITE))
        inputMobileNo.error = getString(R.string.enter_username)
    }

    override fun resetErrorMsg() {
        inputMobileNo?.error = null
    }

    override fun showDialog(title: String, message: String) {
        val builder: AlertDialog.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(getContext())
        }
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes) { dialog, which ->
                // continue with delete
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    override fun showProgressBar() {
        uti.animateView(progress_overlay,View.VISIBLE,0.3f,200)
    }

    override fun hideProgressBar() {
        uti.animateView(progress_overlay,View.GONE,0.4f,200)
    }

    override fun openHomeActivity() {
        val myIntent = Intent(this@Login, MainActivity::class.java)
        startActivity(myIntent)
    }

    override fun setAuthenticationFailedError() {
        edit_mobile_number.setText("")
        inputMobileNo.setError(getString(R.string.authentication_error_msg))
    }

    override fun getContext(): Context {
        return this
    }

    override fun onPostResume() {
        super.onPostResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getContext() != null) {
            if (mLoginPresenter.checkPermissions())
                mLoginPresenter.checkIfUserAlreadyLoggedIn()
        } else
            mLoginPresenter.checkIfUserAlreadyLoggedIn()
    }

    override fun onClick(v: View?) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}