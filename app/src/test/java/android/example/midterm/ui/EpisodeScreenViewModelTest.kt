package android.example.midterm.ui

import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.EpisodesFetchRepository
import android.example.midterm.ui.viewmodel.EpisodeScreenViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EpisodeScreenViewModelTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var testEpisodeScreenViewModel: EpisodeScreenViewModel

    private val testEpisodesFetchRepository = mockk<EpisodesFetchRepository>(relaxed = true){
        coEvery { seasonCall(any(), any()) } returns ServiceResult.Success(mockk(relaxed = true){
            every { Title } returns "Star Wars"
            every { Season } returns "10"
            every { totalSeasons } returns 10

        })

    }

    @Before
    fun setup(){
        testEpisodeScreenViewModel = spyk(
            EpisodeScreenViewModel(testEpisodesFetchRepository)
        )
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

    @Test
    fun `seasonCall post result to episodeArrayLiveData` () = runBlockingTest {
        testEpisodeScreenViewModel.seasonCall("Stonks", "1234")

        verify { testEpisodeScreenViewModel.seasonCall(any(), any()) }

        val result = testEpisodeScreenViewModel.episodeArrayLiveData.value
        result
    }
}