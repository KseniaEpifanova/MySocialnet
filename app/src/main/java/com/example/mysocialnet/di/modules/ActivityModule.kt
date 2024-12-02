package com.example.mysocialnet.di.modules

import com.example.mysocialnet.di.ActivityScope
import com.example.mysocialnet.features.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

}