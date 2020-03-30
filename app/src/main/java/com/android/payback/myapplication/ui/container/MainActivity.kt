package com.android.payback.myapplication.ui.container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.payback.myapplication.R
import com.android.payback.myapplication.model.ImageModel
import com.android.payback.myapplication.ui.Dashboard.ResultsAdapter
import com.android.payback.myapplication.ui.Dashboard.SearchInterface

class MainActivity : AppCompatActivity() , SearchInterface, ResultsAdapter.OnItemClick {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    override fun enterSearchWord(word: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClick(item: ImageModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
