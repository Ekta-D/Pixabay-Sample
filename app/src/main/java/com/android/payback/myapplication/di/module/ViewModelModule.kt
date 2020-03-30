package com.pixabay.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.payback.myapplication.ui.Dashboard.DashboardViewModel
import com.android.payback.myapplication.viewmodel.ViewModelKey
import dagger.Binds
import com.pixabay.utils.di.ViewModelFactory
import dagger.Module
import dagger.multibindings.IntoMap


@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    internal abstract fun dashboardVM(viewModel: DashboardViewModel): ViewModel


}