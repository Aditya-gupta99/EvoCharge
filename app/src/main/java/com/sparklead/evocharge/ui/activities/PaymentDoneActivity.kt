package com.sparklead.evocharge.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.google.android.gms.maps.model.LatLng
import com.sparklead.evocharge.R
import kotlinx.android.synthetic.main.activity_payment_done.*

class PaymentDoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_done)

        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        btn_navigate.setOnClickListener {
            val destination = LatLng(12.9228712, 77.5389588)
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=12.9228712,77.5389588")
            )
            startActivity(intent)
        }

        tv_report_summary.setOnClickListener {
            startActivity(Intent(this,ReportActivity::class.java))
        }
    }

}