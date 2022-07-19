package bowling.domain;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    @RepeatedTest(50)
    void 게임_진행_테스트() {
        BowlingGame bowlingGame = new BowlingGame(new User("lyj"));
        while (bowlingGame.isEnd()) {
            bowlingGame.play();
        }
    }

    @Test
    void 유저_확인() {
        BowlingGame bowlingGame = new BowlingGame(new User("lyj"));
        assertThat(bowlingGame.getUser().getName()).isEqualTo("lyj");
    }

}
