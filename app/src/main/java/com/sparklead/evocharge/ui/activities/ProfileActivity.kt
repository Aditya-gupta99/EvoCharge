package com.sparklead.evocharge.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.sparklead.evocharge.R
import com.sparklead.evocharge.firestore.FirestoreClass
import com.sparklead.evocharge.models.User
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity() {

    private lateinit var mUserDetails : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        if(intent.hasExtra(Constants.USERID))
        {
            //get the user_id details from intent as parcelableExtra
            mUserDetails = intent.getStringExtra(Constants.USERID).toString()
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
                showErrorSnackBar("Enter your Full name", true)
                false
            }
            TextUtils.isEmpty(et_Reg_No.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your Reg No ", true)
                false
            }
            TextUtils.isEmpty(et_mobile_no.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your mobile number", true)
                false
            }
            TextUtils.isEmpty(et_ev_model_profile.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your Model Name", true)
                false
            }
            TextUtils.isEmpty(et_email_profile.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Enter your email RegNo", true)
                false
            }
            else->{
                true
            }
        }
    }

    private fun updateUserProfileDetails()
    {
//        val userHashMap =HashMap<String,Any>()
//
//        val name = et_full_name_profile.text.toString().trim{it <=' '}
//        val evModel = et_ev_model_profile.text.toString().trim{it <= ' '}
//        val email = et_email_profile.text.toString().trim{it <= ' '}
//        val phone = et_mobile_no.text.toString().trim{it <= ' '}
//        val address = et_Reg_No.text.toString().trim{it <= ' '}
//

        val user = User(
            mUserDetails,
            et_full_name_profile.text.toString().trim { it<= ' '},
            et_ev_model_profile.text.toString().trim { it<= ' '},
            et_email_profile.text.toString().trim { it<= ' '},
            et_mobile_no.text.toString().trim { it<= ' '},
            et_Reg_No.text.toString().trim { it<= ' '},
            1
        )

//
//        userHashMap[Constants.FULL_NAME]=name
//        userHashMap[Constants.EV_MODEL]=evModel
//        userHashMap[Constants.EMAIL]=email
//        userHashMap[Constants.MOBILE]=phone
//        userHashMap[Constants.ADDRESS]=address
//        userHashMap[Constants.PROFILE_COMPLETED]=1
//
////
//        FirestoreClass().updateUserProfileData(this,userHashMap)
    }

    fun userRegistrationSuccess(){
        hideProgressDialog()
        showErrorSnackBar("You are registered successfully.",false)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        },1000)

    }

    fun userProfileUpdateSuccess(){
        hideProgressDialog()

        showErrorSnackBar("profile updated successfully",false)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }, 500)
    }
}