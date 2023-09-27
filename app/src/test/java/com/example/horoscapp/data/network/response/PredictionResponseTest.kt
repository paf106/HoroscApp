package com.example.horoscapp.data.network.response

import com.example.horoscapp.domain.model.PredictionModel
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PredictionResponseTest {


    @Test
    fun `toDomain() returns PredictionModel`() {
        val predictionResponse = PredictionResponse("date", "horoscope", "sign")
        val predictionModel = predictionResponse.toDomain()
        assertEquals(predictionModel, PredictionModel("horoscope", "sign"))
    }
}