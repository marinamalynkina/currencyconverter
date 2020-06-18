package com.malynkina.currencyconverter.di

import com.malynkina.currencyconverter.AppActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeAppActivity(): AppActivity
}
