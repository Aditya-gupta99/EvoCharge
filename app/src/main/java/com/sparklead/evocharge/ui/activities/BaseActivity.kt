package com.sparklead.evocharge.ui.activities

import android.app.Dialog
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sparklead.evocharge.R
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity: AppCompatActivity() {


    private var doubleBackToExitPressedOnce = false
    private lateinit var mProgressDialog : Dialog

    fun showErrorSnackBar(message: String, errorMessage: Boolean){
        val snackBar = Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if(errorMessage){
            snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSnackBarError))
        }
        else
        {
            snackBarView.setBackgroundColor(ContextCompat.getColor(this,
                R.color.colorSnackBarSuccess
            ))
        }
        snackBar.show()
    }


    fun showProgressbar(text: String )
    {
        mProgressDialog = Dialog(this)


        /* set the screen content from a layout resource.
        The resource will be inflamed , adding all top-level views to the screen. */

        mProgressDialog.setContentView(R.layout.dialog_progress)

        mProgressDialog.tv_progress_text.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        //Start the dialog and display it on screen.

        mProgressDialog.show()
    }

    fun hideProgressDialog()
    {
        mProgressDialog.dismiss()
    }

    fun doubleBackToExit() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true

        Toast.makeText(
            this,
            "Please click back again to exit",
            Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2500)
    }

}