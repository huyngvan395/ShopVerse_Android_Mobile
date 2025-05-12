package com.example.shopverse

import android.app.Application

class MyApplication : Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(applicationContext)
        instance = this
    }
    companion object {
        lateinit var instance: MyApplication
        val appContainer: AppContainer
            get() = instance.container
    }

}