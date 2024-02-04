package com.agungtriu.weather.data

import com.agungtriu.weather.DataDummy
import com.agungtriu.weather.data.remote.ApiServiceGeo
import com.agungtriu.weather.data.remote.ApiServiceWeather
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class RepositoryTest {

    private lateinit var repository: Repository
    private lateinit var apiServiceWeather: ApiServiceWeather
    private lateinit var apiServiceGeo: ApiServiceGeo

    @Before
    fun setUp() {
        apiServiceGeo = mock()
        apiServiceWeather = mock()
        repository = RepositoryImp(apiServiceGeo, apiServiceWeather)
    }

    @Test
    fun getLocation() = runTest {
        whenever(apiServiceGeo.getLocation()).thenReturn(DataDummy.dummyLocation)
        val actual = repository.getLocation()
        val expected = DataDummy.dummyLocation
        assertEquals(expected, actual)
    }

    @Test
    fun getWeather() = runTest {
        whenever(
            apiServiceWeather.getWeather(
                "",
                "",
                "",
                "",
                ""
            )
        ).thenReturn(DataDummy.dummyWeather)
        val actual = repository.getWeather("", "", "", "", "")
        val expected = DataDummy.dummyWeather
        assertEquals(expected, actual)
    }
}