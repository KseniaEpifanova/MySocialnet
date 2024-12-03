package com.example.mysocialnet.di.modules

import android.app.Application
import android.content.Context
import com.example.mysocialnet.utils.SharedPrefsHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app.baseContext

    @Provides
    @Singleton
    fun provideSharedPrefsHelper(context: Context): SharedPrefsHelper {
        return SharedPrefsHelper(context)
    }

}
