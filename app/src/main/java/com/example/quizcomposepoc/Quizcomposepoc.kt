package com.example.quizcomposepoc

import android.app.Application
import com.example.quizcomposepoc.di.module.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Quizcomposepoc():Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Quizcomposepoc)
            modules(Test.modules())
        }
    }
}