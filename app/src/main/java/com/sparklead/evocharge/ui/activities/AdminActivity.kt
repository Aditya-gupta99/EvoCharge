package com.sparklead.evocharge.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.sparklead.evocharge.R

class AdminActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_admin)
//
//        val items = ArrayList<String>();
//        items.add("Add home charging station")
//        items.add("Add Power charging station")
//        items.add("Add Ev repair Shop")
//        items.add("Add Ev Vehicles Parts")

//    }

    var courses = arrayOf<String?>("Home charging station", "Power charging station",
        "EV repair Shop")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked
        val spin = findViewById<Spinner>(R.id.select_type)
        spin.onItemSelectedListener = this

        // Create the instance of ArrayAdapter
        // having the list of courses
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses)

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spin.adapter = ad
    }

    override fun onItemSelected(parent: AdapterView<*>?,
                                view: View, position: Int,
                                id: Long) {
        // make toastof name of course
        // which is selected in spinner
        Toast.makeText(applicationContext,
            courses[position],
            Toast.LENGTH_SHORT)
            .show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
