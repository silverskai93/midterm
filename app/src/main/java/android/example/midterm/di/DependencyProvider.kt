package android.example.midterm.di

import android.example.midterm.di.appcomponent.AppComponent
import android.example.midterm.di.authcomponent.AuthComponent

object DependencyProvider {
    fun getModules() = listOf(
        *AppComponent.provideModules(),
        *AuthComponent.provideModules()
    )
}