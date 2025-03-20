package com.bjorav.ovinger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

    private final List<PlayingCard> deck;
    private final Random random;

    public DeckOfCards() {
        deck = new ArrayList<>();
        random = new Random();
        char[] suits = {'S', 'H', 'D', 'C'};
        for (char suit : suits) {
            for (int face = 1; face <= 13; face++) {
                deck.add(new PlayingCard(suit, face));
            }
        }
    }

    public List<PlayingCard> dealHand(int n) {
        if (n < 1 || n > 52) {
            throw new IllegalArgumentException("Number of cards must be between 1 and 52");
        }

        List<PlayingCard> hand = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(deck.size());
            hand.add(deck.remove(index));
        }
        return hand;
    }

    public void shuffle() {
        Collections.shuffle(deck, random);
    }

    public int remainingCards() {
        return deck.size();
    }
}