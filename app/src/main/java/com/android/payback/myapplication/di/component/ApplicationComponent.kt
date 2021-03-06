package com.android.payback.myapplication.di.component

import android.app.Application
import com.android.payback.myapplication.di.module.ContextModule
import com.android.payback.myapplication.di.module.FragmentModule
import com.android.payback.myapplication.di.module.RoomModule
import com.android.payback.myapplication.utils.MyApplication
import com.pixabay.di.modules.ActivityModule
import com.pixabay.di.modules.ApplicationModule
import com.pixabay.di.modules.ViewModelModule

import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.DaggerApplication

@Singleton
@Component(modules = [ContextModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class, ViewModelModule::class, ActivityModule::class, FragmentModule::class, RoomModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: MyApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}