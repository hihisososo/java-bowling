package bowling.domain;

import java.security.InvalidParameterException;

public class SecondPitch {

  private static final String INVALID_PIN_NUMBER_MSG = "잘못된 쓰러뜨린 핀 갯수입니다.";
  private final int knockDownPin;

  public SecondPitch(int knockDownPin) {
    assertSecondPitch(knockDownPin);
    this.knockDownPin = knockDownPin;
  }

  private void assertSecondPitch(int knockDownPin) {
    if (knockDownPin > Frame.PIN_NUMBER || knockDownPin < 0) {
      throw new InvalidParameterException(INVALID_PIN_NUMBER_MSG);
    }
  }

  public int getKnockDownPin() {
    return knockDownPin;
  }
}
