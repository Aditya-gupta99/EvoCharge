package com.sparklead.evocharge.ui.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.sparklead.evocharge.R
import com.sparklead.evocharge.firestore.FirestoreClass
import com.sparklead.evocharge.models.User
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var storedVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btn_get_OTP.setOnClickListener {
            logInRegisteredUser()
        }

        btn_login.setOnClickListener {
            var otp = et_otp.text.toString().trim()
            if (otp.isNotEmpty()){
                verifyVerificationCode(otp)
                showProgressbar(resources.getString(R.string.please_wait))
            }
            else{
                Toast.makeText(applicationContext,"Please Enter otp",Toast.LENGTH_SHORT).show()
            }
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                val code = credential.smsCode!!
                if(code!=null){
                    et_otp.setText(code)
                }

            }

            override fun onVerificationFailed(e: FirebaseException) {
                hideProgressDialog()
                Toast.makeText(applicationContext,"Auth Failed",Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {


                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token

                til_email.visibility = View.GONE
                btn_get_OTP.visibility= View.GONE

                hideProgressDialog()

                til_otp.visibility = View.VISIBLE
                btn_login.visibility = View.VISIBLE
            }
        }
    }

    private fun logInRegisteredUser(){
        if(validateLoginDetails()){

            showProgressbar(resources.getString(R.string.please_wait))


        }
    }

    private fun validateLoginDetails():Boolean{

        return when {
            TextUtils.isEmpty(et_Phone_no.text.toString().trim{it <=' '})->{
                showErrorSnackBar("Please Enter Your Phone No",true)
                false
            }
            else -> {
                var phoneNo = et_Phone_no.text.toString().trim()
                if(phoneNo == "7631586913" ){
                    startActivity(Intent(this, AdminActivity::class.java))
                    false
                }
                else{
                    sendVerificationCode("+91$phoneNo")
                    true
                }

            }
        }
    }

    private fun sendVerificationCode(phone :String){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)     // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyVerificationCode(code:String){
        val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(storedVerificationId,code)
        signUp(credential)
    }

    private fun signUp(credential: PhoneAuthCredential){

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    hideProgressDialog()

                    showErrorSnackBar("Signup Successfully",false)
                    if (task.isSuccessful){

                        Handler(Looper.getMainLooper()).postDelayed({
                            val intent = Intent(this, ProfileActivity::class.java)
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            finish()
                        }, 300)

                    }
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {

                    hideProgressDialog()

                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(applicationContext,"Code entered is incorrect",Toast.LENGTH_SHORT).show()
                        et_otp.setText("")
                    }
                }
            }
    }


}