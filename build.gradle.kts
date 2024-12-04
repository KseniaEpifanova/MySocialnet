plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("io.realm.kotlin") version "1.10.0"
}
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.8.4"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}