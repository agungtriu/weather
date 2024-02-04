package com.agungtriu.weather.data.remote

import com.agungtriu.weather.DataDummy
import com.agungtriu.weather.utils.Helper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class ApiServiceGeoTest {
    private lateinit var server: MockWebServer
    private lateinit var apiServiceGeo: ApiServiceGeo

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()

        apiServiceGeo = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceGeo::class.java)
    }

    @After
    fun teardown() {
        server.shutdown()
    }

    @Test
    fun getLocation() = runTest{
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Helper.readTestResourceFile("location.json"))

        server.enqueue(response)

        val actualData = apiServiceGeo.getLocation()
        val expectedData = DataDummy.dummyLocation

        assertEquals(expectedData, actualData)
    }
}