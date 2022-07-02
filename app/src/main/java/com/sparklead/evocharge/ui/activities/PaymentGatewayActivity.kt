package com.sparklead.evocharge.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.gms.maps.model.LatLng
import com.sparklead.evocharge.R
import kotlinx.android.synthetic.main.activity_payment_gateway.*


class PaymentGatewayActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_gateway)

        setupActionBar()

        btn_gPay.setOnClickListener {
            val destination = LatLng(12.9228712, 77.5389588)
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=12.9228712,77.5389588")
            )
            startActivity(intent)
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