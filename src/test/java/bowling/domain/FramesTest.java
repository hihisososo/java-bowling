package bowling.domain;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class FramesTest {
    @RepeatedTest(50)
    void 프레임들의_피칭_진행이_잘_되는지() {
        Frames frames = new Frames();
        while (frames.catPitch()) {
            frames.pitch();
        }
    }

    @Test
    void 프레임_피칭_진행_에러() {
        Frames frames = new Frames();
        while (frames.catPitch()) {
            frames.pitch();
        }
        assertThatThrownBy(() -> frames.pitch())
                .isInstanceOf(InvalidParameterException.class);
    }
}
