package com.example.mysocialnet

import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDexApplication
import com.example.mysocialnet.di.AppInjector
import com.example.mysocialnet.di.components.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MySocialNetApp : MultiDexApplication(), HasAndroidInjector, LifecycleObserver {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
        appComponent = AppInjector.appComponent

    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }
}