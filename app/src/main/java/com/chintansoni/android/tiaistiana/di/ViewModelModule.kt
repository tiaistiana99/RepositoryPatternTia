package com.chintansoni.android.tiaistiana.di

import com.chintansoni.android.tiaistiana.model.UserRepository
import com.chintansoni.android.tiaistiana.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Dependency: ListViewModel
    viewModel {
        ListViewModel(get())
    }

    // Dependency: UserRepository
    single {
        UserRepository(get(), get())
    }
}