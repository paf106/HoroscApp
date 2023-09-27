package com.example.horoscapp.ui.providers

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class RandomCardProviderTest {

    @Test
    fun `getLuckyCard() returns a card`() {
        val randomCardProvider = RandomCardProvider()
        val luckyCard = randomCardProvider.getLuckyCard()
        assertNotNull(luckyCard)
    }

}
