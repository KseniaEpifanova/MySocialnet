package com.example.mysocialnet.di.components

import android.app.Application
import com.example.mysocialnet.MySocialNetApp
import com.example.mysocialnet.di.modules.ActivityModule
import com.example.mysocialnet.di.modules.AppModule
import com.example.mysocialnet.di.modules.FragmentModule
import com.example.mysocialnet.di.modules.NetworkModule
import com.example.mysocialnet.di.modules.ServiceModule
import com.example.mysocialnet.di.modules.ViewModelModule
import com.example.mysocialnet.features.login.LoginViewModel
import com.example.mysocialnet.features.register.RegisterViewModel
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
        FragmentModule::class,
        ServiceModule::class,
        ViewModelModule::class
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

    fun inject(loginViewModel: LoginViewModel)
    fun inject(registerViewModel: RegisterViewModel)
}
