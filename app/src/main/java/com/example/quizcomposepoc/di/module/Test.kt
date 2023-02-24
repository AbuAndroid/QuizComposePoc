package com.example.quizcomposepoc.di.module


import com.example.quizcomposepoc.homescreenUi.QuizViewModel
import com.example.quizcomposepoc.repository.QuizRepository
import com.example.quizcomposepoc.utils.AssertManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Test {
    fun modules() =  repoModule + viewModelModule + commonModule
}

val repoModule = module {
    single {
        QuizRepository(get())
    }
}

val viewModelModule = module {
    viewModel{
        QuizViewModel(get())
    }
}

val commonModule = module {
    single {
        AssertManager(androidContext())
    }
}