package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class NormalFrameTest {

  @RepeatedTest(20)
  @DisplayName("프레임 피칭 결과가 잘 생성되는지 확인")
  void play() {
    NormalFrame frame = new NormalFrame();
    while (frame.isPlayable()) {
      frame.play();
    }
    int pitchResult = getPitchResult(frame.getScore());

    assertThat(frame.getScore().getStatus()).isNotIn(Stauts.EMPTY, Stauts.NEED_SECOND_PITCH);
    assertThat(pitchResult).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(10);
  }

  private int getPitchResult(Score score) {
    if (score.getStatus() == Stauts.STRIKE) {
      return BowlingGame.PIN_NUMBER;
    }
    return score.getFirstKnockDownPin() + score.getSecondKnockDownPin();
  }
}
