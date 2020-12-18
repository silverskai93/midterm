package android.example.midterm.data.repo

import android.example.midterm.data.api.Api
import android.example.midterm.data.impl.SeasonsFetchRepositoryImpl
import android.example.midterm.data.model.model.ApiResponse
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.util.assertEqual
import android.example.midterm.util.assertType
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi

import io.mockk.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import java.lang.Exception

@ExperimentalCoroutinesApi
class SeasonFetchRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var testSeasonFetchRepo: SeasonsFetchRepository
    private val testService = mockk<Api>(relaxed = true)
    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        testSeasonFetchRepo = spyk(
            SeasonsFetchRepositoryImpl(testService, testDispatcher)
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `seriesCall returns successful response`() = runBlockingTest {
        // SET UP
        coEvery { testSeasonFetchRepo.seriesCall(any()) } returns ServiceResult.Success(mockk(relaxed = true) {
            every { Title } returns "The Simpsons"
            every { totalSeasons } returns 1444
            every { imdbID } returns "FAKE ID"
        })

//        coEvery { testSeasonFetchRepo.seriesCall(any()) } returns mockk(relaxed = true)

        //TEST
        val result = testSeasonFetchRepo.seriesCall("The Simpsons")

//        Assert.assertEquals(result, ServiceResult.Success<ApiResponse>(data = ApiResponse(
//            Title = "The Simpsons",
//            totalSeasons = 1444,
//            imdbID = "FAKE ID"
//        )))

        result.assertType<ServiceResult.Success<ApiResponse>>{
            this.data.Title.assertEqual("The Simpsons")
            this.data.totalSeasons.assertEqual(1444)
            this.data.imdbID.assertEqual("FAKE ID")
            //Add assert equals for the remaining properties
        }
//        assertEquals("The Simpsons", (result as ApiResponse).Title)
    }
    //Do test for null body response

    //Do tesst for error response
    @Test
    fun `seriesCall returns error` () = runBlockingTest {
        coEvery { testSeasonFetchRepo.seriesCall(any()) } returns ServiceResult.Error(mockk(relaxed = true){
            every { message } returns "ERROR"
            every { cause } returns mockk(relaxed = true){
                every { message } returns "CRACKERS"
            }

        })

        val result = testSeasonFetchRepo.seriesCall("error")

        result.assertType<ServiceResult.Error> {
            this.exception.message.assertEqual("ERROR")
            this.exception.cause?.message.assertEqual("CRACKERS")
        }
    }

}