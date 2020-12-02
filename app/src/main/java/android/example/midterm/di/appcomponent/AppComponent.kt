package android.example.midterm.di.appcomponent


object AppComponent {


    fun provideModules() = arrayOf(
        *DataModule.provideModules()
    )
}