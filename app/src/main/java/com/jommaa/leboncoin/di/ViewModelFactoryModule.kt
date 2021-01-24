package com.jommaa.leboncoin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jommaa.leboncoin.di.annotation.ViewModelKey
import com.jommaa.leboncoin.factory.ViewModelFactory
import com.jommaa.leboncoin.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class ViewModelFactoryModule {

    @Binds
    @Singleton
   abstract fun providesViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel


}