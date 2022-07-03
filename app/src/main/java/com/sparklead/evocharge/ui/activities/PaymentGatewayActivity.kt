package com.sparklead.evocharge.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.gms.maps.model.LatLng
import com.sparklead.evocharge.R
import kotlinx.android.synthetic.main.activity_payment_gateway.*


class PaymentGatewayActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_gateway)
        setupActionBar()
        btn_gPay.setOnClickListener {
            showProgressbar(resources.getString(R.string.please_wait))
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this,PaymentDoneActivity::class.java))
                hideProgressDialog()
            },2000)
        }

    }
    private fun setupActionBar(){

        setSupportActionBar(toolbar_payment)

        val actionBar = supportActionBar
        if (actionBar !=null)
        {
            supportActionBar?.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
        toolbar_payment.setNavigationOnClickListener{
            onBackPressed()
        }
    }


}