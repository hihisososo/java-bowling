package bowling.domain;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.security.InvalidParameterException;

import static bowling.utl.RandomValueGenerator.getRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {

    @Test
    void 프레임_생성() {
        NormalFrame normalFrame = new NormalFrame();
        assertThat(normalFrame.canPitch()).isEqualTo(true);
        assertThat(normalFrame.getRemainPin()).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"0|10", "5|5", "10|0"}, delimiter = '|')
    void 남은핀_확인(int downPin, int expect) {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.pitch(downPin);

        assertThat(normalFrame.getRemainPin()).isEqualTo(expect);
    }

    @RepeatedTest(50)
    void 프레임_피칭은_1에서_2사이() {
        NormalFrame normalFrame = new NormalFrame();

        int pitchCount = 0;
        while (normalFrame.canPitch()) {
            pitchCount++;
            normalFrame.pitch(getRandom(normalFrame.getRemainPin()));
        }

        assertThat(pitchCount).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(2);
    }

    @Test
    void 피칭_초과_에러() {
        NormalFrame firstStrikeFrame = new NormalFrame();
        NormalFrame threePitchFrame = new NormalFrame();

        firstStrikeFrame.pitch(10);
        threePitchFrame.pitch(0);
        threePitchFrame.pitch(5);

        assertThatThrownBy(() -> firstStrikeFrame.pitch(1)).isInstanceOf(InvalidParameterException.class);
        assertThatThrownBy(() -> threePitchFrame.pitch(2)).isInstanceOf(InvalidParameterException.class);
    }

}
