package android.example.midterm.data.repo

import android.example.midterm.data.api.Api2
import android.example.midterm.data.impl.EpisodesFetchRepositoryImpl
import android.example.midterm.data.model.model.EpisodeData
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.util.assertEqual
import android.example.midterm.util.assertType
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EpisodesFetchRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var testEpisodesFetchRepo: EpisodesFetchRepository
    private val testService = mockk<Api2>(relaxed = true)
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp(){
        testEpisodesFetchRepo = spyk(
            EpisodesFetchRepositoryImpl(testService, testDispatcher)
        )
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

    @Test
    fun `seasonCall returns successful response` () = runBlockingTest {
        coEvery { testEpisodesFetchRepo.seasonCall(any(), any()) } returns ServiceResult.Success(
            mockk(relaxed = true){
                every { Title } returns "Game of Thrones"
                every { totalSeasons } returns 100
                every { Season } returns "Unending"

            })

        val result = testEpisodesFetchRepo.seasonCall("Game of Thrones", "Useless")

        result.assertType<ServiceResult.Success<EpisodeData>> {
            this.data.Title.assertEqual("Game of Thrones")
            this.data.totalSeasons.assertEqual(100)
            this.data.Season.assertEqual("Unending")
        }
    }
    @Test
    fun `seasonCall returns error` () = runBlockingTest {
        coEvery { testEpisodesFetchRepo.seasonCall(any(), any()) } returns ServiceResult.Error(mockk(relaxed = true){
            every { message } returns "ERROR"
            every { cause } returns mockk(relaxed = true){
                every { message } returns "WITH MILK"
            }
        })
        val result = testEpisodesFetchRepo.seasonCall("Error", "Error too")

        result.assertType<ServiceResult.Error> {
            this.exception.message.assertEqual("ERROR")
            this.exception.cause?.message.assertEqual("WITH MILK")
        }
    }

}