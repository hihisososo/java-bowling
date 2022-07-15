package bowling.domain;

import static bowling.utl.RandomValueGenerator.getRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FinalFrameTest {

    @Test
    void 프레임_생성() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame.canPitch()).isEqualTo(true);
        assertThat(finalFrame.getRemainPin()).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"0|10", "5|5", "10|0"}, delimiter = '|')
    void 남은핀_확인(int downPin, int expect) {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.pitch(downPin);

        assertThat(finalFrame.getRemainPin()).isEqualTo(expect);
    }

    @RepeatedTest(50)
    void 프레임_피칭을_여러번_했을_때() {
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
        FinalFrame firstStrikeFrame = new FinalFrame();
        FinalFrame threePitchFrame = new FinalFrame();

        firstStrikeFrame.pitch(10);
        threePitchFrame.pitch(0);
        threePitchFrame.pitch(5);

        assertThatThrownBy(() -> firstStrikeFrame.pitch(1)).isInstanceOf(InvalidParameterException.class);
        assertThatThrownBy(() -> threePitchFrame.pitch(2)).isInstanceOf(InvalidParameterException.class);
    }

}
