package com.example.horoscapp.data.network.response

import com.example.horoscapp.domain.model.PredictionModel

data class PredictionResponse(
    val date: String,
    val horoscope: String,
    val sign: String
) {
    fun toDomain(): PredictionModel {
        return PredictionModel(horoscope, sign)
    }
}

