package com.sparklead.evocharge.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sparklead.evocharge.R
import com.sparklead.evocharge.databinding.ActivityMapBinding
import com.sparklead.evocharge.models.Locations


class MapActivity : BaseActivity(), OnMapReadyCallback ,GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private var mSelected :Int  =0

    companion object{
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE)

            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude,location.longitude)
                placeMarker(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,15f))
            }
        }
    }


    private fun placeMarker(currentLatLong: LatLng) {


        val location = ArrayList<Locations>();

         location.add(Locations(13.035675 , 77.5881522 , "Repairing Shop" , 100f))
         location.add(Locations(12.9228712 , 77.5389588 , "Charging Station"  , 7.0f))
        location.add(Locations(12.913229829112801 , 77.56733620562215 , "Charging Station" , 197f))


        for (i in location)
        {
            val x = LatLng(i.lat,i.lng)
            val icon = BitmapDescriptorFactory.defaultMarker(i.hue)
            mMap.addMarker(MarkerOptions().position(x).icon(icon).title(i.station_type))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(x, 15f))
        }

//        val location1 = LatLng(13.035675, 77.5881522)
//        mMap.addMarker(MarkerOptions().position(location1).title("Repairing Shop"))
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 15f))
//
//
//        val location2 = LatLng(12.9228712, 77.5389588)
//        mMap.addMarker(MarkerOptions().position(location2).title("Charging Station"))
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 15f))
//
//
//        val location3 = LatLng(12.913229829112801, 77.56733620562215)
//        mMap.addMarker(MarkerOptions().position(location3).title("Charging Station"))
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 15f))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        mMap.uiSettings.setAllGesturesEnabled(true)

        setUpMap()
    }

    override fun onMarkerClick(p0: Marker): Boolean {

        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)


//        val btnClose = view.findViewById<Button>(R.id.btn_dismiss)
//        btnClose.setOnClickListener {
//            dialog.dismiss()
//        }

        val btnBook = view.findViewById<Button>(R.id.btn_book)
        btnBook.setOnClickListener{

            val dialog2 = BottomSheetDialog(this)
            val view1 = layoutInflater.inflate(R.layout.book_sheet_dialog,null)
            dialog2.setCancelable(true)
            dialog2.setContentView(view1)
            dialog2.show()
            fun selected(cvSlot: CardView, tvSlot: TextView){

                cvSlot.elevation = 50.00f
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    cvSlot.outlineAmbientShadowColor = ContextCompat.getColor(this , R.color.first_color)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    cvSlot.outlineSpotShadowColor = ContextCompat.getColor(this,R.color.first_color)
                }
                tvSlot.setTextColor(Color.parseColor("#17A398"))
            }
            fun unselected(cvSlot : CardView,tvSlot : TextView) {

                cvSlot.elevation = 20f
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    cvSlot.outlineSpotShadowColor = ContextCompat.getColor(this,R.color.black)
                }
                tvSlot.setTextColor(Color.parseColor("#7A8089"))

            }
            val cvSlot1 = view1.findViewById<CardView>(R.id.cv_slot1)
            val cvSlot2 = view1.findViewById<CardView>(R.id.cv_slot2)
            val cvSlot3 = view1.findViewById<CardView>(R.id.cv_slot3)
            val cvSlot4 = view1.findViewById<CardView>(R.id.cv_slot4)
            val text1   = view1.findViewById<TextView>(R.id.tv_slot1)
            val text2   = view1.findViewById<TextView>(R.id.tv_slot2)
            val text3   = view1.findViewById<TextView>(R.id.tv_slot3)
            val text4   = view1.findViewById<TextView>(R.id.tv_slot4)
            val btnProceed = view1.findViewById<Button>(R.id.btn_proceed)

            cvSlot1.setOnClickListener {
                selected(cvSlot1,text1)
                unselected(cvSlot2,text2)
                unselected(cvSlot3,text3)
                unselected(cvSlot4,text4)
                mSelected=1
            }
            cvSlot2.setOnClickListener {
                unselected(cvSlot1,text1)
                selected(cvSlot2,text2)
                unselected(cvSlot3,text3)
                unselected(cvSlot4,text4)
                mSelected=2
            }
            cvSlot3.setOnClickListener {
                unselected(cvSlot1,text1)
                unselected(cvSlot2,text2)
                selected(cvSlot3,text3)
                unselected(cvSlot4,text4)
                mSelected=3

            }
            cvSlot4.setOnClickListener {

                unselected(cvSlot1,text1)
                unselected(cvSlot2,text2)
                unselected(cvSlot3,text3)
                selected(cvSlot4,text4)
                mSelected=4

            }

            btnProceed.setOnClickListener {
                showProgressbar(resources.getString(R.string.please_wait))

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this,PaymentGatewayActivity::class.java))
                },2000)
            }

//            slot1.setOnClickListener{
//                selectedOption(cv_slot1,1)
//            }
//            slot2.setOnClickListener {
//                selectedOption(cv_slot2,2)
//            }
//            slot3.setOnClickListener {
//                selectedOption(cv_slot3,3)
//            }
//            slot4.setOnClickListener {
//                selectedOption(cv_slot4,4)
//            }

        }

        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
        return true
    }
}