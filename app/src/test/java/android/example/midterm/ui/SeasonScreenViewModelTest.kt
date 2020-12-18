package android.example.midterm.ui

import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.SeasonsFetchRepository
import android.example.midterm.ui.viewmodel.SeasonScreenViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SeasonScreenViewModelTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()
    private lateinit var testSeasonScreenViewModel: SeasonScreenViewModel

    private val testSeasonsFetchRepository = mockk<SeasonsFetchRepository>(relaxed = true){
        coEvery { seriesCall(any()) } returns ServiceResult.Success(mockk(relaxed = true){
            every { Title } returns "Power Rangers"
            every { totalSeasons } returns 50
            every { imdbID } returns "FAKE ID"
        })
    }

    @Before
    fun setup(){
        testSeasonScreenViewModel = spyk(
            SeasonScreenViewModel(testSeasonsFetchRepository)
        )
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

    @Test
    fun `seriesCall createserieslist` () = runBlockingTest {
        testSeasonScreenViewModel.seriesCall()

        verify { testSeasonScreenViewModel.seriesCall() }

    }

}