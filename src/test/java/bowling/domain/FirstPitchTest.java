package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class FirstPitchTest {

  @ParameterizedTest
  @DisplayName("첫 볼링 투구가 잘 생성되는지 확인")
  @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
  void make(int knockDownPin) {
    FirstPitch firstFitch = new FirstPitch(knockDownPin);
    assertThat(firstFitch.getKnockDownPin()).isEqualTo(knockDownPin);
  }

  @ParameterizedTest
  @DisplayName("두번째 투구가 필요한 경우를 확인")
  @CsvSource(value = {"1|true", "5|true", "10|false"}, delimiter = '|')
  void needSecondPitch(int knockDownPin, boolean expect) {
    FirstPitch firstFitch = new FirstPitch(knockDownPin);
    assertThat(firstFitch.needSecondPitch()).isEqualTo(expect);
  }

  @ParameterizedTest
  @DisplayName("잘못된 넘어뜨린 핀 개수 입력시 에러")
  @ValueSource(ints = {-1, 11})
  void exception(int knockDownPin) {
    assertThatThrownBy(() -> new FirstPitch(knockDownPin)).isInstanceOf(
        InvalidParameterException.class);
  }
}
