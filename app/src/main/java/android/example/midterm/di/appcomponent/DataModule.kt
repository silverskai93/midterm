package android.example.midterm.di.appcomponent

import android.example.midterm.data.impl.EpisodesFetchRepositoryImpl
import android.example.midterm.data.impl.OverviewFetchRepositoryImpl
import android.example.midterm.data.impl.SeasonsFetchRepositoryImpl
import android.example.midterm.data.repo.EpisodesFetchRepository
import android.example.midterm.data.repo.OverviewFetchRepository
import android.example.midterm.data.repo.SeasonsFetchRepository
import org.koin.dsl.module

class DataModule {

    private fun seasonRepoModule() = module {
        single <SeasonsFetchRepository> { SeasonsFetchRepositoryImpl(get(), get()) }
    }

    private fun episodeRepoModule() = module {
        single <EpisodesFetchRepository> { EpisodesFetchRepositoryImpl(get(), get()) }
    }

    private fun overviewRepoModule() = module {
        single <OverviewFetchRepository> { OverviewFetchRepositoryImpl(get(), get()) }
    }

    fun getModules() = arrayOf(
        seasonRepoModule(),
        episodeRepoModule(),
        overviewRepoModule()
    )

    companion object {
        fun provideModules() = DataModule().getModules()
    }
}