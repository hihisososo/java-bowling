package bowling.domain;

import java.security.InvalidParameterException;

public class NormalPitch {

  private static final String INVALID_PIN_MSG = "쓰러뜨린 핀 개수가 적합하지 않습니다.";
  private final int downPin;
  private final Status status;

  private NormalPitch(int downPin, Status status) {
    this.downPin = downPin;
    this.status = status;
  }

  public static NormalPitch first(int downPin) {
    assertFirstPitch(downPin);
    return new NormalPitch(downPin, getStatus(downPin));
  }

  public NormalPitch second(int secondDownPin) {
    assertSecondPitch(secondDownPin);

    int pinSum = this.downPin + secondDownPin;
    if (pinSum == 0) {
      return new NormalPitch(secondDownPin, Status.MISS);
    } else if (pinSum == BowlingGame.PIN_NUMBER) {
      return new NormalPitch(secondDownPin, Status.SPARE);
    }
    return new NormalPitch(secondDownPin, Status.HIT);
  }

  private static Status getStatus(int downPin) {
    if (downPin == BowlingGame.PIN_NUMBER) {
      return Status.STRIKE;
    } else if (downPin == 0) {
      return Status.GUTTER;
    }
    return Status.HIT;
  }

  private static void assertFirstPitch(int downPin) {
    if (downPin < 0 || downPin > BowlingGame.PIN_NUMBER) {
      throw new InvalidParameterException(INVALID_PIN_MSG);
    }
  }

  private void assertSecondPitch(int secondDownPin) {
    int pinSum = this.downPin + secondDownPin;
    if (pinSum < 0 || pinSum > BowlingGame.PIN_NUMBER) {
      throw new InvalidParameterException(INVALID_PIN_MSG);
    }
  }

  public int getDownPin() {
    return downPin;
  }

  public Status getStatus() {
    return status;
  }
}
