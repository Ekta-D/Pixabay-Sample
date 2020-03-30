package com.android.payback.myapplication.utils


import com.android.payback.myapplication.di.component.ApplicationComponent
import com.android.payback.myapplication.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val component = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)
        return component
    }


}