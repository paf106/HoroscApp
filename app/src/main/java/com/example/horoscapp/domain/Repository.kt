package com.example.horoscapp.domain

import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.model.PredictionModel

interface Repository {

    suspend fun getPrediction(sign: String) : PredictionModel?
}