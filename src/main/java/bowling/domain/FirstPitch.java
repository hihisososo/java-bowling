package bowling.domain;

import java.security.InvalidParameterException;

public class FirstPitch {

  private static final String INVALID_PIN_NUMBER_MSG = "잘못된 쓰러뜨린 핀 갯수입니다.";
  private final int knockDownPin;

  public FirstPitch(int knockDownPin) {
    assertFirstFitch(knockDownPin);
    this.knockDownPin = knockDownPin;
  }

  private void assertFirstFitch(int knockDownPin) {
    if (knockDownPin > BowlingGame.PIN_NUMBER || knockDownPin < 0) {
      throw new InvalidParameterException(INVALID_PIN_NUMBER_MSG);
    }
  }

  public int getKnockDownPin() {
    return knockDownPin;
  }

  public boolean isStrike() {
    return knockDownPin == BowlingGame.PIN_NUMBER;
  }
}
