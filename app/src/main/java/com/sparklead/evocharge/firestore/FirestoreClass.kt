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

class FirestoreClass {
    private val mFirestore =FirebaseFirestore.getInstance()

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String,Any>)
    {
        mFirestore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userHashMap)
            .addOnCompleteListener{
                when(activity)
                {
                    is ProfileActivity ->
                    {
                        activity.userProfileUpdateSuccess()
                    }
                }
            }
            .addOnFailureListener{ e ->
                when(activity)
                {
                    is ProfileActivity  ->
                    {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,"Error while updating the user_id details",e
                )
            }
    }

    private fun getCurrentUserId() :String {

        //An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        //A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserId = ""
        if(currentUser!=null)
        {
            currentUserId = currentUser.uid
        }

        return currentUserId

    }

}