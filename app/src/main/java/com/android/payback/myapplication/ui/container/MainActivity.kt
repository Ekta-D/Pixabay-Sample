package com.android.payback.myapplication.ui.container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.payback.myapplication.R

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dashboard)
    }

}
