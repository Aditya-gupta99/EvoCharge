package com.sparklead.evocharge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    val id: String = "",
    val name : String = "",
    val evModel :String ="",
    val email :String = "",
    val phone :String = "",
    val address :String = "",
    val profileCompleted:Int = 0
):Parcelable