package com.sparklead.evocharge.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sparklead.evocharge.R
import com.sparklead.evocharge.databinding.FragmentHomeBinding
import com.sparklead.evocharge.ui.activities.AdminActivity
import com.sparklead.evocharge.ui.activities.MapActivity
import com.sparklead.evocharge.ui.activities.ReportActivity
import kotlinx.android.synthetic.main.app_bar_dashboard.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root =inflater.inflate(R.layout.fragment_home,container,false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cv_Map.setOnClickListener {
            val intent = Intent(context,MapActivity::class.java)
            startActivity(intent)
        }
    }
}