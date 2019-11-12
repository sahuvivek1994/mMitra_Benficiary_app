package tech.inscripts.ins_armman.mmitra_app.login
import android.content.ContentValues
import android.content.Context
import com.example.mmitraprogramteam.data.retrofit.RemoteDataSource
import org.json.JSONObject
import tech.inscripts.ins_armman.mmitra_app.data.database.DatabaseContract
import tech.inscripts.ins_armman.mmitra_app.data.model.UserDetails
import tech.inscripts.ins_armman.mmitra_app.utility.Utility


class LoginInteractor : ILoginInteractor
{


    val remoteData = RemoteDataSource()
    var utility= Utility()


    override fun login(userDetails: UserDetails, onLoginFinished: ILoginInteractor.OnLoginFinished, context: Context) {
       val remotedatasource = remoteData.getInstance()
        val authService = remotedatasource.getAuthService()
        authService.getAuthentication(userDetails, onLoginFinished, context)
    }

    override fun saveUserDetails(username: String, password: String, jsonObject: JSONObject) {

            deleteUserDetails()

            //val jsonObjectUser = jsonObject.getJSONObject("data")

            val values = ContentValues()

            //values.put(DatabaseContract.LoginTable.COLUMN_USER_ID, jsonObjectUser.optString("id"))
           // values.put(DatabaseContract.LoginTable.COLUMN_NAME, jsonObjectUser.optString("name"))
            values.put(DatabaseContract.LoginTable.COLUMN_USERNAME, username)
            values.put(DatabaseContract.LoginTable.COLUMN_PASSWORD, password)
           // values.put(DatabaseContract.LoginTable.COLUMN_PHONE_NO, jsonObjectUser.optString("phoneno"))

            utility.getDatabase().insert(DatabaseContract.LoginTable.TABLE_NAME, null, values)

    }

    override fun deleteUserDetails() {
                utility.getDatabase().delete(DatabaseContract.LoginTable.TABLE_NAME, null, null)
            }

    override fun userAlreadyLoggedIn(): Boolean {
        val cursor = utility.getDatabase().rawQuery("SELECT * FROM " + DatabaseContract.LoginTable.TABLE_NAME, null)
        return cursor.moveToFirst()    }


}