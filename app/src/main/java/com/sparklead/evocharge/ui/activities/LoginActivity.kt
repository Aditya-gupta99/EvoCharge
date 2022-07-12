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
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.sparklead.evocharge.R
import com.sparklead.evocharge.firestore.FirestoreClass
import com.sparklead.evocharge.models.User
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_signUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        btn_signIn.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

    }
}