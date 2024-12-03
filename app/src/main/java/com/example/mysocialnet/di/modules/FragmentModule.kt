package com.example.mysocialnet.di.modules

import com.example.mysocialnet.di.FragmentScope
import com.example.mysocialnet.features.details.ImageDetailsFragment
import com.example.mysocialnet.features.home.HomeFragment
import com.example.mysocialnet.features.login.LoginFragment
import com.example.mysocialnet.features.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeImageDetailsFragment(): ImageDetailsFragment
}