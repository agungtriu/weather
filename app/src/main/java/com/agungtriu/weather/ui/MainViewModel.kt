package com.agungtriu.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungtriu.weather.data.Repository
import com.agungtriu.weather.data.remote.GeoResponse
import com.agungtriu.weather.data.remote.WeatherResponse
import com.agungtriu.weather.utils.Menu
import com.agungtriu.weather.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var _resultLocation = MutableLiveData<UIState<GeoResponse>>()
    val resultLocation: LiveData<UIState<GeoResponse>> get() = _resultLocation

    private var _resultWeather = MutableLiveData<UIState<WeatherResponse>>()
    val resultWeather: LiveData<UIState<WeatherResponse>> get() = _resultWeather

    private var lat = "0.0"
    private var lon = "0.0"
    var selectedMenu = Menu.TEMPERATURE.string

    init {
        getLocation()
    }

    fun getLocation() {
        viewModelScope.launch {
            _resultLocation.postValue(UIState.Loading)
            try {
                val result = repository.getLocation()
                _resultLocation.postValue(UIState.Success(result))
                getWeather()
            } catch (t: Throwable) {
                _resultLocation.postValue(UIState.Error(t.message.toString()))
            }
        }
    }

    fun getWeather(
        exclude: String? = null,
        units: String? = "metric",
        lang: String? = null
    ) {
        viewModelScope.launch {
            _resultWeather.postValue(UIState.Loading)
            try {
                val result = repository.getWeather(lat, lon, exclude, units, lang)
                _resultWeather.postValue(UIState.Success(result))
            } catch (t: Throwable) {
                _resultWeather.postValue(UIState.Error(t.message.toString()))
            }
        }
    }
}