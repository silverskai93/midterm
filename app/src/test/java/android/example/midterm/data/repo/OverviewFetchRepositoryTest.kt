package android.example.midterm.data.repo

import android.example.midterm.data.api.Api3
import android.example.midterm.data.impl.OverviewFetchRepositoryImpl
import android.example.midterm.data.model.model.OverViewData
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.util.assertEqual
import android.example.midterm.util.assertType
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class OverviewFetchRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var testOverviewFetchRepo: OverviewFetchRepository
    private val testService = mockk<Api3>(relaxed = true)
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp(){
        testOverviewFetchRepo = spyk(
            OverviewFetchRepositoryImpl(testService, testDispatcher)
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `overviewCall returns successful` () = runBlockingTest {
        coEvery { testOverviewFetchRepo.overviewCall(any(), any()) } returns ServiceResult.Success(
            mockk(relaxed = true){
                every { Title } returns "The Simpsons"
                every { Season } returns 20
                every { Episode } returns 10
            })

        val result = testOverviewFetchRepo.overviewCall("The Simpsons", "SIMPS")

        result.assertType<ServiceResult.Success<OverViewData>> {
            this.data.Title.assertEqual("The Simpsons")
            this.data.Season.assertEqual(20)
            this.data.Episode.assertEqual(10)
        }
    }

    @Test
    fun `overviewCall returns error`() = runBlockingTest {
        coEvery { testOverviewFetchRepo.overviewCall(any(), any()) } returns ServiceResult.Error(
            mockk(relaxed = true){
                every { message } returns "ERROR"
                every { cause } returns mockk(relaxed = true){
                    every { message } returns "NO SPICE"
                }
            })
        val result = testOverviewFetchRepo.overviewCall("error", "error")

        result.assertType<ServiceResult.Error> {
            this.exception.message.assertEqual("ERROR")
            this.exception.cause?.message.assertEqual("NO SPICE")
        }

    }
}