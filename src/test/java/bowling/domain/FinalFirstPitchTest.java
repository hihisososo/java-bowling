package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFirstPitchTest {

    @ParameterizedTest
    @CsvSource(value = {"10|STRIKE", "9|HIT", "1|HIT", "0|GUTTER"}, delimiter = '|')
    void 피치_생성(int downPin, Status status) {
        FinalFirstPitch normalPitch = new FinalFirstPitch(downPin);
        assertThat(normalPitch.getStatus()).isEqualTo(status);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 피치_에러(int downPin) {
        assertThatThrownBy(() -> new FinalFirstPitch(downPin))
                .isInstanceOf(InvalidParameterException.class);
    }
}