package android.example.midterm.di.authcomponent

object AuthComponent {
    fun provideModules() = arrayOf(
        *ViewModelModule.provideModules()
    )
}