// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false

    id ("com.android.library") version "7.3.0" apply false
    id ("com.google.dagger.hilt.android") version "2.43.2" apply false
}