package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalBonusPitchTest {

    @ParameterizedTest
    @CsvSource(value = {"0|10|0|GUTTER", "0|10|5|HIT", "0|10|10|STRIKE", "5|5|0|GUTTER", "5|5|5|HIT", "5|5|10|STRIKE", "10|0|0|MISS", "10|5|0|GUTTER",
            "10|5|5|SPARE", "10|10|0|GUTTER", "10|10|5|HIT", "10|10|10|STRIKE"}, delimiter = '|')
    void 피치_생성(int firstDownPin, int secondDownPin, int bonusPin, Status statusExpect) {
        FinalFirstPitch firstPitch = new FinalFirstPitch(firstDownPin);
        FinalSecondPitch secondPitch = new FinalSecondPitch(firstPitch, secondDownPin);
        FinalBonusPitch finalBonusPitch = new FinalBonusPitch(firstPitch, secondPitch, bonusPin);
        assertThat(finalBonusPitch.getStatus()).isEqualTo(statusExpect);
    }

    @ParameterizedTest
    @CsvSource(value = {"0|0", "0|5", "0|9", "5|4", "8|1", "9|0"}, delimiter = '|')
    void 보너스_피치_불가능_에러(int firstDownPin, int secondDownPin) {
        FinalFirstPitch firstPitch = new FinalFirstPitch(firstDownPin);
        FinalSecondPitch secondPitch = new FinalSecondPitch(firstPitch, secondDownPin);

        assertThatThrownBy(() -> new FinalBonusPitch(firstPitch, secondPitch, 1))
                .isInstanceOf(InvalidParameterException.class);
    }

}
