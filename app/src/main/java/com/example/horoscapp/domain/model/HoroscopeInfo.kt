package com.example.horoscapp.domain.model

import com.example.horoscapp.R

sealed class HoroscopeInfo(val name: Int, val image: Int) {


    data object Aries : HoroscopeInfo(R.string.aries, R.drawable.aries)
    data object Taurus : HoroscopeInfo(R.string.taurus, R.drawable.tauro)
    data object Gemini : HoroscopeInfo(R.string.gemini, R.drawable.geminis)
    data object Cancer : HoroscopeInfo(R.string.cancer, R.drawable.cancer)
    data object Leo : HoroscopeInfo(R.string.leo, R.drawable.leo)
    data object Virgo : HoroscopeInfo(R.string.virgo, R.drawable.virgo)
    data object Libra : HoroscopeInfo(R.string.libra, R.drawable.libra)
    data object Scorpio : HoroscopeInfo(R.string.scorpio, R.drawable.escorpio)
    data object Sagittarius : HoroscopeInfo(R.string.sagittarius, R.drawable.sagitario)
    data object Capricorn : HoroscopeInfo(R.string.capricorn, R.drawable.capricornio)
    data object Aquarius : HoroscopeInfo(R.string.aquarius, R.drawable.aquario)
    data object Pisces : HoroscopeInfo(R.string.pisces, R.drawable.piscis)
}
