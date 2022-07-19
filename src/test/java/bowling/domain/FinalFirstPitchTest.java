package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFirstPitchTest {

    @ParameterizedTest
    @CsvSource(value = {"10|STRIKE", "9|HIT", "1|HIT", "0|GUTTER"}, delimiter = '|')
    void 피치_생성(int downPin, Status status) {
        FinalFirstPitch pitch = new FinalFirstPitch(downPin);
        assertThat(pitch.getStatus()).isEqualTo(status);
    }

    @ParameterizedTest
    @CsvSource(value = {"10|true", "9|false", "1|false", "0|flase"}, delimiter = '|')
    void 스트라이크_여부_확인(int downPin, boolean expect) {
        assertThat(new FinalFirstPitch(downPin).isStrike()).isEqualTo(expect);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 피치_에러(int downPin) {
        assertThatThrownBy(() -> new FinalFirstPitch(downPin))
                .isInstanceOf(InvalidParameterException.class);
    }


}