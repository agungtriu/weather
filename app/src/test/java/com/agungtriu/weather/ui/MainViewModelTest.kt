package com.agungtriu.weather.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.agungtriu.weather.DataDummy
import com.agungtriu.weather.data.Repository
import com.agungtriu.weather.data.remote.GeoResponse
import com.agungtriu.weather.data.remote.WeatherResponse
import com.agungtriu.weather.utils.MainDispatcherRule
import com.agungtriu.weather.utils.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = Mockito.mock()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getResultLocation() = runTest {
        whenever(repository.getLocation()).thenReturn(DataDummy.dummyLocation)
        val actual = mutableListOf<UIState<GeoResponse>>()
        mainViewModel = MainViewModel(repository)
        mainViewModel.resultLocation.observeForever {
            actual.add(it)
        }
        advanceUntilIdle()
        assertEquals(
            listOf(
                UIState.Loading,
                UIState.Success(DataDummy.dummyLocation)
            ),
            actual
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getResultWeather() = runTest {
        whenever(repository.getWeather("", "", "", "", "")).thenReturn(DataDummy.dummyWeather)
        val actual = mutableListOf<UIState<WeatherResponse>>()
        mainViewModel = MainViewModel(repository)
        mainViewModel.resultWeather.observeForever {
            actual.add(it)
        }
        advanceUntilIdle()
        assertEquals(
            listOf(
                UIState.Loading,
                UIState.Success(DataDummy.dummyWeather)
            ),
            actual
        )
    }
}