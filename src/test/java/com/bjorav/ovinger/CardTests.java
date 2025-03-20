package com.bjorav.ovinger;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlayingCardTest {

    @Test
    void testCreateValidCard() {
        PlayingCard card = new PlayingCard('H', 10);
        assertEquals('H', card.getSuit());
        assertEquals(10, card.getFace());
    }

    @Test
    void testGetAsString() {
        PlayingCard card = new PlayingCard('S', 13);
        assertEquals("S13", card.getAsString());
    }

    @Test
    void testInvalidSuit() {
        assertThrows(IllegalArgumentException.class, () -> new PlayingCard('X', 5));
    }

    @Test
    void testInvalidFace() {
        assertThrows(IllegalArgumentException.class, () -> new PlayingCard('H', 14));
    }

    @Test
    void testEquals() {
        PlayingCard card1 = new PlayingCard('H', 10);
        PlayingCard card2 = new PlayingCard('H', 10);
        PlayingCard card3 = new PlayingCard('S', 10);

        assertEquals(card1, card2);
        assertNotEquals(card1, card3);
    }
}

class DeckOfCardsTest {

    @Test
    void testNewDeckSize() {
        DeckOfCards deck = new DeckOfCards();
        assertEquals(52, deck.remainingCards());
    }

    @Test
    void testDealHand() {
        DeckOfCards deck = new DeckOfCards();
        List<PlayingCard> hand = deck.dealHand(5);

        assertEquals(5, hand.size());
        assertEquals(47, deck.remainingCards());
    }

    @Test
    void testInvalidDealAmount() {
        DeckOfCards deck = new DeckOfCards();
        assertThrows(IllegalArgumentException.class, () -> deck.dealHand(53));
    }

    @Test
    void testCardUniqueness() {
        DeckOfCards deck = new DeckOfCards();
        List<PlayingCard> hand = deck.dealHand(20);

        Set<String> uniqueCards = new HashSet<>();
        for (PlayingCard card : hand) {
            uniqueCards.add(card.getAsString());
        }

        assertEquals(20, uniqueCards.size());
    }

    @Test
    void testShuffle() {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        assertEquals(52, deck.remainingCards());
    }
}