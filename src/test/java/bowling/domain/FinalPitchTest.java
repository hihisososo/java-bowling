package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalPitchTest {

  @ParameterizedTest
  @CsvSource(value = {"10|STRIKE", "9|HIT", "1|HIT", "0|GUTTER"}, delimiter = '|')
  void 첫피치_생성(int downPin, Status status) {
    FinalPitch finalPitch = FinalPitch.first(downPin);
    assertThat(finalPitch.getStatus()).isEqualTo(status);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 11})
  void 첫피치_에러(int downPin) {
    assertThatThrownBy(() -> FinalPitch.first(downPin))
            .isInstanceOf(InvalidParameterException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"0|0|MISS", "0|1|HIT", "2|4|HIT", "0|10|SPARE", "5|5|SPARE", "10|10|STRIKE", "10|5|HIT", "10|0|GUTTER"}, delimiter = '|')
  void 두번째_피치_생성(int firstDownPin, int secondDownPin, Status status) {
    FinalPitch secondPitch = FinalPitch.first(firstDownPin).second(secondDownPin);
    assertThat(secondPitch.getStatus()).isEqualTo(status);
  }

  @ParameterizedTest
  @CsvSource(value = {"0|-1", "5|6", "10|-1", "-1|-1"}, delimiter = '|')
  void 두번째_피치_에러(int first, int second) {
    assertThatThrownBy(() -> FinalPitch.first(first).second(second))
            .isInstanceOf(InvalidParameterException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"10|10|10|STRIKE", "10|10|0|GUTTER", "10|10|5|HIT", "5|5|10|STRIKE", "5|5|5|HIT", "5|5|0|GUTTER", "0|10|10|STRIKE", "0|10|5|HIT", "0|10|0|GUTTER"}, delimiter = '|')
  void 보너스_피치_생성(int firstDownPin, int secondDownPin, int bonusDownPin, Status status) {
    FinalPitch first = FinalPitch.first(firstDownPin);
    FinalPitch second = first.second(secondDownPin);
    FinalPitch bonus = FinalPitch.bonus(first, second);

    assertThat(bonus.getStatus()).isEqualTo(status);
  }
}
