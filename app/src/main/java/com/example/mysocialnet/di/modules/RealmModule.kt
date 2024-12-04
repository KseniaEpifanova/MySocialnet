package com.example.mysocialnet.di.modules

import com.example.mysocialnet.models.db.UserDB
import dagger.Module
import dagger.Provides
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
object RealmModule {

    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(schema = setOf(UserDB::class))
            .deleteRealmIfMigrationNeeded()
            .build()
        return Realm.open(config)
    }
}
