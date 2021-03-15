package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;

import java.util.Collections;
import java.util.List;

public abstract class HandState {
    protected static final int BLACKJACK_SCORE = 21;
    protected static final int INITIAL_CARD_GIVEN = 2;
    protected static final int NO_ACE = 0;

    protected List<Card> cards;

    public abstract HandState add(final Card cards);

    public abstract boolean isBust();

    public abstract boolean isBlackjack();

    public final int calculateScore() {
        final int maximumSum = cards.stream()
                .mapToInt(card -> card.getCardLetter().getValue())
                .sum();

        if (maximumSum > BLACKJACK_SCORE && countAce() > NO_ACE) {
            return adjustScoreWithAce(maximumSum);
        }
        return maximumSum;
    }

    private final int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private final int adjustScoreWithAce(final int maximumSum) {
        int aceCount = countAce();
        int adjustSum = maximumSum;
        while (aceCount > 0 && adjustSum > BLACKJACK_SCORE) {
            adjustSum = adjustSum - CardLetter.ACE.getValue() + CardLetter.ACE.getExtraValue();
            aceCount--;
        }
        return adjustSum;
    }

    public final List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
