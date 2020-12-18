package android.example.midterm.ui

import android.example.midterm.data.model.model.OverViewData
import android.example.midterm.data.model.networkmodel.ServiceResult
import android.example.midterm.data.repo.OverviewFetchRepository
import android.example.midterm.ui.overviewscreen.OverviewScreenViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.parameter.emptyParametersHolder

@ExperimentalCoroutinesApi
class OverviewScreenViewModelTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var testOverviewScreenViewModel: OverviewScreenViewModel
    private val dispatcher = mockk<Dispatchers>(relaxed = true)
    private val testOverViewFetchRepository = mockk<OverviewFetchRepository>(relaxed = true){
        coEvery { overviewCall(any(), any()) } returns ServiceResult.Success(mockk(relaxed = true){
            every { Title } returns "Star Wars"
            every { Season } returns 1337
            every { Episode } returns 80085
        })
    }

    @Before
    fun setup() {
        testOverviewScreenViewModel = spyk(
            OverviewScreenViewModel(dispatcher, testOverViewFetchRepository)
        )
        testOverViewFetchRepository
        //testOverviewScreenViewModel.overviewArrayLiveData.value = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `overviewCall post result to overviewArrayLiveData`() = runBlockingTest {
        // Set Up
//        every { testOverviewScreenViewModel.overviewCall(any(), any()) } just runs
        coEvery { testOverviewScreenViewModel["handleOverviewCall"](allAny<ServiceResult.Success<OverViewData>>()) as Unit } just runs


        //Test
        testOverviewScreenViewModel.overviewCall("stonk", "1234")

        //verify { testOverviewScreenViewModel.overviewCall(any(), any()) }

        val result = testOverviewScreenViewModel.overviewArrayLiveData.value
        result

    }

}