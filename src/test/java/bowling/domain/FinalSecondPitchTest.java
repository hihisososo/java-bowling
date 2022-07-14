package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalSecondPitchTest {

    @ParameterizedTest
    @CsvSource(value = {"0|0|MISS", "0|1|HIT", "2|4|HIT", "0|10|SPARE", "5|5|SPARE", "10|5|HIT", "10|10|STRIKE"}, delimiter = '|')
    void 피치_생성(int firstDownPin, int secondDownPin, Status status) {
        FinalFirstPitch firstPitch = new FinalFirstPitch(firstDownPin);
        FinalSecondPitch secondPitch = new FinalSecondPitch(firstPitch, secondDownPin);
        assertThat(secondPitch.getStatus()).isEqualTo(status);
    }

    @ParameterizedTest
    @CsvSource(value = {"0|-1", "5|6", "10|11"}, delimiter = '|')
    void 피치_에러(int firstDownPin, int secondDownPin) {
        assertThatThrownBy(() -> new FinalSecondPitch(new FinalFirstPitch(firstDownPin), secondDownPin))
                .isInstanceOf(InvalidParameterException.class);
    }

}
