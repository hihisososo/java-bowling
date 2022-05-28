package bowling.domain;

import java.security.InvalidParameterException;
import java.util.Arrays;

public enum FirstPitch {
  EMPTY(-1, ' '),
  GUTTER(0, '-'), DOWN_1(1, '1'), DOWN_2(2, '2'), DOWN_3(3, '3'),
  DOWN_4(4, '4'), DOWN_5(5, '5'), DOWN_6(6, '6'), DOWN_7(7, '7'),
  DOWN_8(8, '8'), DOWN_9(9, '9'), STRIKE(10, 'X');

  public static final String INVALID_DOWN_MSG = "넘어뜨린 핀 개수는 " + values().length + " 개를 초과할 수 없습니다.";

  private final int downPin;
  private final char expression;

  FirstPitch(int downPin, char expression) {
    this.downPin = downPin;
    this.expression = expression;
  }

  public static FirstPitch from(int downPin) {
    if (downPin < 0 || downPin > BowlingGame.PIN_NUMBER) {
      throw new InvalidParameterException(INVALID_DOWN_MSG);
    }
    return Arrays.stream(values())
        .filter(firstPitch -> firstPitch.downPin == downPin)
        .findFirst().get();
  }

  public int getDownPin() {
    return downPin;
  }

  public char getExpression() {
    return expression;
  }
}
