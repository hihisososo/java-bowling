package domain.state;

import domain.Pins;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class StrikeTest {

    @Test
    @DisplayName("스트라이크 아님 예외처리")
    void bowl() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Strike(Pins.of(5));
        });
    }

    @Test
    @DisplayName("더이상 추가 금지 예외처리")
    void bowlExcpetion() {
        Strike strike = new Strike(Pins.ALL);
        Assertions.assertThrows(RuntimeException.class, () -> {
            strike.bowl(Pins.of(4));
        });
    }
}