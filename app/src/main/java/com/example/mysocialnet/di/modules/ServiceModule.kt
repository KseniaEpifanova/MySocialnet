package com.example.mysocialnet.di.modules

import com.example.mysocialnet.services.ApiService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract fun contributeApiService(): ApiService
}
