package tech.inscripts.ins_armman.mmitra_app.utility

import android.content.Context

interface IMvpView {
    fun getContext() : Context
    interface OnFilterResultListener{
        fun onZeroResult(emptyMsg: String)

        fun onMoreThanZeroResult(resultSize: Int)
    }
}