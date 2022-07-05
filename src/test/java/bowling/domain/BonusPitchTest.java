package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusPitchTest {

  @ParameterizedTest
  @CsvSource(value = {"10|10|10|STRIKE", "10|10|0|GUTTER", "10|10|5|HIT", "5|5|10|STRIKE", "5|5|5|HIT", "5|5|0|GUTTER", "0|10|10|STRIKE", "0|10|5|HIT", "0|10|0|GUTTER"}, delimiter = '|')
  void 보너스_피치_생성(int firstDownPin, int secondDownPin, int thirdDownPin, Status status) {
    FinalPitch first = FinalPitch.first(firstDownPin);
    FinalPitch second = first.second(secondDownPin);
    FinalBonusPitch finalBonusPitch = new FinalBonusPitch(first, second);

    assertThat(finalBonusPitch.getStatus()).isEqualTo(status);
  }
}
