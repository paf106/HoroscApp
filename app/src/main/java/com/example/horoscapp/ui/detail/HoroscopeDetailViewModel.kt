package com.example.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state
    lateinit var horosocope: HoroscopeModel

    fun getHoroscope(sign: HoroscopeModel) {
        horosocope = sign
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            val result = withContext(Dispatchers.IO) {
                getPredictionUseCase(sign.name)

            }
            result?.let {
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horosocope)
            } ?: run {
                _state.value = HoroscopeDetailState.Error("Error :(")
            }
        }
    }
}