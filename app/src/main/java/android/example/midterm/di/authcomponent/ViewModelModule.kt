package android.example.midterm.di.authcomponent

import android.example.midterm.ui.viewmodel.EpisodeScreenViewModel
import android.example.midterm.ui.viewmodel.OverviewScreenViewModel
import android.example.midterm.ui.viewmodel.SeasonScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {

    private fun seasonModule() = module {
        viewModel { SeasonScreenViewModel(get()) }
    }

    private fun episodeModule() = module {
        viewModel { EpisodeScreenViewModel(get()) }
    }

    private fun overviewModule() = module {
        viewModel { OverviewScreenViewModel(get()) }
    }

    private fun getModules() = arrayOf(
        seasonModule(),
        episodeModule(),
        overviewModule()
    )

    companion object {
        fun provideModules() = ViewModelModule().getModules()
    }
}