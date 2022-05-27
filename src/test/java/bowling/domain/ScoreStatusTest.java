package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreStatusTest {

  @ParameterizedTest
  @DisplayName("첫 피칭 결과를 가지고 결과가 잘 생성되는지 확인")
  @CsvSource(value = {"1|NEED_SECOND_PITCH", "5|NEED_SECOND_PITCH", "10|STRIKE"}, delimiter = '|')
  void nextStatusFirst(int knowkDownPin, ScoreStauts expect) {
    ScoreStauts stauts = ScoreStauts.EMPTY;
    stauts = stauts.nextStatus(new FirstPitch(knowkDownPin));

    assertThat(stauts).isEqualTo(expect);
  }

  @ParameterizedTest
  @DisplayName("두번째 피칭 결과를 가지고 결과가 잘 생성되는지 확인")
  @CsvSource(value = {"1|2|MISS", "5|5|SPARE", "5|4|MISS", "0|0|GUTTER"}, delimiter = '|')
  void nextStatusSecond(int first, int second, ScoreStauts expect) {
    ScoreStauts stauts = ScoreStauts.EMPTY;
    stauts = stauts.nextStatus(new FirstPitch(first), new SecondPitch(second));

    assertThat(stauts).isEqualTo(expect);
  }

  @Test
  @DisplayName("첫 피칭과 두번째 피칭이 핀 개수 초과시 에러")
  void exception() {
    assertThatThrownBy(() -> {
      ScoreStauts stauts = ScoreStauts.EMPTY;
      stauts.nextStatus(new FirstPitch(5), new SecondPitch(6));
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
