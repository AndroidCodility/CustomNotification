package com.codility.customnotification.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.codility.customnotification.R
import kotlinx.android.synthetic.main.display.*

/**
 * Created by Govind on 3/1/2018.
 */
class NotificationViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        try {
            val intent = intent
            val title = intent.getStringExtra("title")
            val msg = intent.getStringExtra("msg")
            tvTitle.text = title
            tvMessage.text = msg
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("getStringExtra_EX", e.toString() + "")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}