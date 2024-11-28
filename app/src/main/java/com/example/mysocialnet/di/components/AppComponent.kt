package com.example.mysocialnet.di.components

import android.app.Application
import com.example.mysocialnet.MySocialNetApp
import com.example.mysocialnet.di.modules.ActivityModule
import com.example.mysocialnet.di.modules.AppModule
import com.example.mysocialnet.di.modules.NetworkModule
import com.example.mysocialnet.di.modules.ServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [AndroidSupportInjectionModule::class,
        NetworkModule::class,
        AppModule::class,
        ActivityModule::class,
        ServiceModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: MySocialNetApp)

    fun app(): Application
}
