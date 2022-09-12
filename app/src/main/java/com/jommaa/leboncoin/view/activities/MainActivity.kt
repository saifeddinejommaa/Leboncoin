package com.jommaa.leboncoin.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jommaa.leboncoin.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}