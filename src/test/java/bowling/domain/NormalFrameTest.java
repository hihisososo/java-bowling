package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class NormalFrameTest {

  @Test
  void 첫_프레임_생성() {
    NormalFrame normalFrame = NormalFrame.first();
    assertThat(normalFrame.canPitch()).isEqualTo(true);
  }

  @Test
  void 프레임_두번째_생성() {
    NormalFrame secondFrame = NormalFrame.first().next();
    assertThat(secondFrame.canPitch()).isEqualTo(true);
  }

  @RepeatedTest(50)
  void 프레임_피칭이_잘_채워지는지() {
    NormalFrame normalFrame = NormalFrame.first();
    while (normalFrame.canPitch()) {
      normalFrame.pitch();
    }
    assertThat(normalFrame.getPitchSize()).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(2);
  }

}
