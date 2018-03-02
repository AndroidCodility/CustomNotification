package com.codility.customnotification.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.codility.customnotification.R
import com.codility.customnotification.utils.CustomNotification
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Govind on 3/1/2018.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btNotification -> {
                if (!isValidate()) {
                    return
                }
                CustomNotification().showNotification(this, edTitle.text.toString(), edMessage.text.toString())
            }

            R.id.btCustomNotification -> {
                if (!isValidate()) {
                    return
                }
                CustomNotification().showCustomNotification(this, edTitle.text.toString(), edMessage.text.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btNotification.setOnClickListener(this)
        btCustomNotification.setOnClickListener(this)
    }

    private fun isValidate(): Boolean {
        if (edTitle.text.isEmpty()) {
            showToast("Title is empty..!!")
            return false
        }
        if (edMessage.text.isEmpty()) {
            showToast("Message is empty..!!")
            return false
        }
        return true
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}