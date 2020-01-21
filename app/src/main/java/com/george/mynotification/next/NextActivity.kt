package com.george.mynotification.next

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.george.mynotification.R

import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        setSupportActionBar(toolbar)
    }

}
