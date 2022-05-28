package bowling.domain;

import java.security.InvalidParameterException;
import java.util.Arrays;

public enum SecondPitch {
  EMPTY(-1, ' '), NOT_NEED_PITCH(-1, ' '), SPARE(-1, 'X'), MISS(0, '-'),
  DOWN_1(1, '1'), DOWN_2(2, '2'), DOWN_3(3, '3'),
  DOWN_4(4, '4'), DOWN_5(5, '5'), DOWN_6(6, '6'),
  DOWN_7(7, '7'), DOWN_8(8, '8'), DOWN_9(9, '9');

  public static final String INVALID_DOWN_MSG = "넘어뜨린 핀 개수는 " + values().length + " 개를 초과할 수 없습니다.";

  private final int downPin;
  private final char expression;

  SecondPitch(int downPin, char expression) {
    this.downPin = downPin;
    this.expression = expression;
  }

  public static SecondPitch from(FirstPitch firstPitch, int downPin) {
    if (firstPitch == FirstPitch.STRIKE) {
      return NOT_NEED_PITCH;
    }

    assertDownPin(firstPitch, downPin);
    int allDown = firstPitch.getDownPin() + downPin;
    if (allDown == BowlingGame.PIN_NUMBER) {
      return SPARE;
    }

    return Arrays.stream(values())
        .filter(secondPitch -> secondPitch.downPin == downPin)
        .findFirst().get();
  }

  private static void assertDownPin(FirstPitch firstPitch, int downPin) {
    assertDownPin(firstPitch, downPin);
    int allDown = firstPitch.getDownPin() + downPin;
    if (allDown < 0 || allDown > BowlingGame.PIN_NUMBER) {
      throw new InvalidParameterException(INVALID_DOWN_MSG);
    }
  }
}
