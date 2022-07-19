package bowling.domain;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.security.InvalidParameterException;

import static bowling.utl.RandomValueGenerator.getRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {

    @Test
    void 프레임_생성() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame.canPitch()).isEqualTo(true);
        assertThat(finalFrame.getRemainPin()).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"0|0|10", "0|5|5", "0|10|10", "5|0|5", "5|5|10", "10|0|10", "10|5|5", "10|10|10"}, delimiter = '|')
    void 남은핀_확인(int firstDownPin, int secondDownPin, int expect) {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.pitch(firstDownPin);
        finalFrame.pitch(secondDownPin);

        assertThat(finalFrame.getRemainPin()).isEqualTo(expect);
    }

    @RepeatedTest(50)
    void 프레임_피칭은_1에서_3사이() {
        FinalFrame finalFrame = new FinalFrame();

        int pitchCount = 0;
        while (finalFrame.canPitch()) {
            pitchCount++;
            finalFrame.pitch(getRandom(finalFrame.getRemainPin()));
        }

        assertThat(pitchCount).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(3);
    }

    @Test
    void 피칭_초과_에러() {
        FinalFrame bonusFrame = new FinalFrame();
        FinalFrame noBonusFrame = new FinalFrame();

        bonusFrame.pitch(10);
        bonusFrame.pitch(10);
        bonusFrame.pitch(10);
        noBonusFrame.pitch(5);
        noBonusFrame.pitch(4);

        assertThatThrownBy(() -> bonusFrame.pitch(2)).isInstanceOf(InvalidParameterException.class);
        assertThatThrownBy(() -> noBonusFrame.pitch(2)).isInstanceOf(InvalidParameterException.class);
    }

}
