package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.security.InvalidParameterException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NormalPitchTest {

  @ParameterizedTest
  @CsvSource(value = {"10|STRIKE", "9|HIT", "1|HIT", "0|GUTTER"}, delimiter = '|')
  void 첫피치_생성(int downPin, Status status) {
    NormalPitch normalPitch = NormalPitch.first(downPin);
    assertThat(normalPitch.getStatus()).isEqualTo(status);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 11})
  void 첫피치_에러(int downPin) {
    assertThatThrownBy(() -> NormalPitch.first(downPin))
        .isInstanceOf(InvalidParameterException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"0|0|MISS", "0|1|HIT", "2|4|HIT", "0|10|SPARE", "5|5|SPARE"}, delimiter = '|')
  void 두번째_피치_생성(int firstDownPin, int secondDownPin, Status status) {
    NormalPitch secondPitch = NormalPitch.first(firstDownPin).second(secondDownPin);
    assertThat(secondPitch.getStatus()).isEqualTo(status);
  }

  @ParameterizedTest
  @CsvSource(value = {"0|-1", "5|6", "10|1"}, delimiter = '|')
  void 두번째_피치_에러(int first, int second) {
    assertThatThrownBy(() -> NormalPitch.first(first).second(second))
        .isInstanceOf(InvalidParameterException.class);
  }

}
