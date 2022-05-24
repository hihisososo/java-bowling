package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrameScoreTest {

  @Test
  @DisplayName("스트라이크가 생성되는지 확인")
  void make(){
    FrameScore frameScore = FrameScore.of(new FirstPitch(10));
    assertThat(frameScore).isEqualTo(FrameScore.STRIKE);
  }

}
