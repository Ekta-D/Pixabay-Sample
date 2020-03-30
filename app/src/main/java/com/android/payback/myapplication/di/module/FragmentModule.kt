package com.android.payback.myapplication.di.module

import com.android.payback.myapplication.ui.container.DashboardFragment
import com.android.payback.myapplication.ui.detailScreen.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDetailFragment(): DetailFragment

}