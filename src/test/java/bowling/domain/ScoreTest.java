package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.PitchNotRecordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreTest {

  @Test
  @DisplayName("점수의 초기화가 잘 되는지 확인")
  void init() {
    Score score = Score.init();
    assertThat(score.getStatus()).isEqualTo(Stauts.EMPTY);
  }

  @Test
  @DisplayName("점수 기록되지 않았을 때 가져오려 하면 에러")
  void exception() {
    assertThatThrownBy(() -> Score.init().getFirstKnockDownPin()).isInstanceOf(
        PitchNotRecordException.class);
    assertThatThrownBy(() -> Score.init().getSecondKnockDownPin()).isInstanceOf(
        PitchNotRecordException.class);
  }

  @ParameterizedTest
  @DisplayName("첫 피칭 결과를 통해 점수의 업데이트가 잘 되는지 확인")
  @CsvSource(value = {"1|NEED_SECOND_PITCH", "5|NEED_SECOND_PITCH", "10|STRIKE"}, delimiter = '|')
  void record(int knockDownPin, Stauts expect) {
    Score score = Score.init();
    FirstPitch firstPitch = new FirstPitch(knockDownPin);

    score.record(firstPitch);

    assertThat(score.getFirstKnockDownPin()).isEqualTo(firstPitch.getKnockDownPin());
    assertThat(score.getStatus()).isEqualTo(expect);
  }

  @ParameterizedTest
  @DisplayName("첫번째와 두번째 피칭 결과를 통해 점수의 업데이트가 잘 되는지 확인")
  @CsvSource(value = {"5|5|SPARE", "5|4|MISS", "0|0|GUTTER"}, delimiter = '|')
  void recordSecond(int first, int second, Stauts expect) {
    Score score = Score.init();
    FirstPitch firstPitch = new FirstPitch(first);
    SecondPitch secondPitch = new SecondPitch(second);

    score.record(firstPitch);
    score.record(secondPitch);

    assertThat(score.getFirstKnockDownPin()).isEqualTo(firstPitch.getKnockDownPin());
    assertThat(score.getSecondKnockDownPin()).isEqualTo(secondPitch.getKnockDownPin());
    assertThat(score.getStatus()).isEqualTo(expect);
  }
}
