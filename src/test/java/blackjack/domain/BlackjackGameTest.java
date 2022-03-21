package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private static final int INITIAL_CARD_NUMBER = 2;

    @Test
    @DisplayName("drawStringCard는 초기 카드를 뽑는다.")
    void drawStartingCard() {
        Participant roma = new Player("roma", 0);
        Participant tonic = new Player("tonic", 0);
        BlackjackGame blackjackGame = new BlackjackGame(new Participants(List.of(roma, tonic)), new Deck());

        blackjackGame.drawStartingCard();
        Participant dealer = blackjackGame.getDealer();

        assertAll(
                () -> assertThat(roma.getCards().size()).isEqualTo(INITIAL_CARD_NUMBER),
                () -> assertThat(tonic.getCards().size()).isEqualTo(INITIAL_CARD_NUMBER),
                () -> assertThat(dealer.getCards().size()).isEqualTo(INITIAL_CARD_NUMBER)
        );
    }
}