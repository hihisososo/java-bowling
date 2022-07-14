package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalSecondPitchTest {

    @ParameterizedTest
    @CsvSource(value = {"0|0|MISS", "0|1|HIT", "2|4|HIT", "0|10|SPARE", "5|5|SPARE", "5|0|GUTTER"}, delimiter = '|')
    void 피치_생성(int firstDownPin, int secondDownPin, Status status) {
        NormalFirstPitch firstPitch = new NormalFirstPitch(firstDownPin);
        NormalSecondPitch secondPitch = new NormalSecondPitch(firstPitch, secondDownPin);
        assertThat(secondPitch.getStatus()).isEqualTo(status);
    }

    @ParameterizedTest
    @CsvSource(value = {"0|-1", "5|6", "10|1"}, delimiter = '|')
    void 피치_에러(int firstDownPin, int secondDownPin) {
        assertThatThrownBy(() -> new NormalSecondPitch(new NormalFirstPitch(firstDownPin), secondDownPin))
                .isInstanceOf(InvalidParameterException.class);
    }

}
