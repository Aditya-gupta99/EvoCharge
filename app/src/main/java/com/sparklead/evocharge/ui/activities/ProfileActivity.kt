package com.sparklead.evocharge.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.sparklead.evocharge.R
import com.sparklead.evocharge.firestore.FirestoreClass
import com.sparklead.evocharge.models.User
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity() {

    var flag :Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if (flag){
                val intent =Intent(this, UserActivity::class.java)
                startActivity(intent)
        }



        btn_profile.setOnClickListener {
            if (validateUserProfileDetails()){
                showProgressbar(resources.getString(R.string.please_wait))

                updateUserProfileDetails()
            }
        }

    }

    private fun validateUserProfileDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_full_name_profile.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your mobile number", true)
                false
            }
            TextUtils.isEmpty(et_Address.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your mobile number", true)
                false
            }
            TextUtils.isEmpty(et_mobile_no.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your mobile number", true)
                false
            }
            TextUtils.isEmpty(et_ev_model_profile.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your mobile number", true)
                false
            }
            TextUtils.isEmpty(et_email_profile.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your mobile number", true)
                false
            }
            else->{
                true
            }
        }
    }

    private fun updateUserProfileDetails()
    {
        val userHashMap =HashMap<String,Any>()

        val name = et_full_name_profile.text.toString().trim{it <=' '}
        val evModel = et_ev_model_profile.text.toString().trim{it <= ' '}
        val email = et_email_profile.text.toString().trim{it <= ' '}
        val phone = et_mobile_no.text.toString().trim{it <= ' '}
        val address = et_Address.text.toString().trim{it <= ' '}


        userHashMap[Constants.FULL_NAME]=name
        userHashMap[Constants.EV_MODEL]=evModel
        userHashMap[Constants.EMAIL]=email
        userHashMap[Constants.MOBILE]=phone
        userHashMap[Constants.ADDRESS]=address
        userHashMap[Constants.PROFILE_COMPLETED]=1
        flag=true


        FirestoreClass().updateUserProfileData(this,userHashMap)
    }

    fun userProfileUpdateSuccess(){
        hideProgressDialog()

        showErrorSnackBar("profile updated successfully",false)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }, 500)
    }
}