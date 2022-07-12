package com.sparklead.evocharge.ui.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sparklead.evocharge.R
import com.sparklead.evocharge.firestore.FirestoreClass
import com.sparklead.evocharge.models.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class   SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_signUp.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser()
    {

        if(validateRegisterDetails())
        {

            showProgressbar(resources.getString(R.string.please_wait))

            val email :String = et_email_signup.text.toString().trim{ it <= ' ' }
            val password : String = et_password_signup.text.toString().trim{ it <= ' '}


            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                OnCompleteListener<AuthResult>{ task ->

                    hideProgressDialog()

                    if(task.isSuccessful){

                        val firebaseUser : FirebaseUser = task.result!!.user!!

                        val user = User(
                            firebaseUser.uid,
                            et_name_signup.text.toString().trim{ it<=' ' },
                            et_email_signup.text.toString().trim{ it <= ' '},


                            )

                        FirestoreClass().registerUser(this , user)

//                        FirebaseAuth.getInstance().signOut()
//                        finish()
                    }
                    else
                    {
                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
            )
        }
    }


    private fun validateRegisterDetails():Boolean {
        return when {
            TextUtils.isEmpty(et_name_signup.text.toString().trim{it<= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.err_msg_first_name),true)
                false
            }
            TextUtils.isEmpty(et_email_signup.text.toString().trim{it<= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.err_msg_email),true)
                false
            }
            TextUtils.isEmpty(et_password_signup.text.toString().trim{it<= ' '}) ->
            {
                showErrorSnackBar(resources.getString(R.string.err_msg_password),true)
                false
            }
            !cb_terms_and_condition.isChecked ->
            {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_confirm),true)
                false
            }
            else ->
            {
//                showErrorSnackBar("Thanks for registering!",false)
                true
            }
        }
    }

    fun userRegistrationSuccess(){
        hideProgressDialog()
        showErrorSnackBar("You are registered successfully.",false)


        Handler(Looper.getMainLooper()).postDelayed({
            FirebaseAuth.getInstance().signOut()
            finish()
        },1000)

    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}