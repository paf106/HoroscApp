package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.motherobject.HoroscopeMotherObject
import com.example.horoscapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var horoscopeViewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `When viewmodel is created then horoscopes are loaded`() {
        every { horoscopeProvider.getHoroscope() } returns horoscopeInfoList
        horoscopeViewModel = HoroscopeViewModel(horoscopeProvider)
        val horoscopes = horoscopeViewModel.horoscope.value
        assertNotNull(horoscopes)
    }

}
