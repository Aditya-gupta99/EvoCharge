package com.sparklead.evocharge.models

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
class Locations(

    val lat: Double = 0.00,
    val lng: Double = 0.00,
    val station_type: String ="",
    val hue : Float = 0f

):Parcelable

