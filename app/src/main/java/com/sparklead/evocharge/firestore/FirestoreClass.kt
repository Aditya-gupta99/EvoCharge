package com.sparklead.evocharge.firestore

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.sparklead.evocharge.models.User
import com.sparklead.evocharge.ui.activities.Constants
import com.sparklead.evocharge.ui.activities.LoginActivity
import com.sparklead.evocharge.ui.activities.ProfileActivity
import com.sparklead.evocharge.ui.activities.SignUpActivity
import kotlin.math.absoluteValue

class FirestoreClass {
    private val mFirestore = FirebaseFirestore.getInstance()


    fun registerUser(activity: SignUpActivity, userInfo: User){

        //the "users" is a collection name.
        mFirestore.collection(Constants.USERS)
            //Document Id for users fields.
            .document(userInfo.id)
            //here the userInfo are field and the setOption is set to merge.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                //Here call a function of base activity for transferring the result to it.
                activity.userRegistrationSuccess()
            }


            .addOnFailureListener { e->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user_id.",
                    e
                )
            }

    }
}

