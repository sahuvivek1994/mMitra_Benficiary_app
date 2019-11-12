package tech.inscripts.ins_armman.mmitra_app.utility

interface IBasePresenter<V> {
    fun attachView(view :V)
    fun detachView()
}