package android.example.midterm.di.appcomponent

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


object AppComponent {

    private fun dispatchersProvider() = module {
    single { Dispatchers }
    }


    fun provideModules() = arrayOf(
        *DataModule.provideModules(),
        dispatchersProvider()
    )
}