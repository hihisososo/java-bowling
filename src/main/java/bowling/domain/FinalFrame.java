package bowling.domain;

import java.security.InvalidParameterException;
import java.util.Optional;

public class FinalFrame implements Frame {

  public static final String NO_MORE_PITCH = "더이상 피칭할 수 없습니다.";
  private FinalFirstPitch finalFirstPitch;
  private FinalSecondPitch finalSecondPitch;
  private FinalBonusPitch finalBonusPitch;
  private int remainPin;

  public FinalFrame() {
    this.remainPin = BowlingGame.PIN_NUMBER;
  }

  @Override
  public void pitch(int downPin) {
    if (isBonusExist()) {
      throw new InvalidParameterException(NO_MORE_PITCH);
    }
    if (isSecondExist() && !isStrikeOrSpareUntilSecond()) {
      throw new InvalidParameterException(NO_MORE_PITCH);
    }
    remainPin -= downPin;
    if (!isFirstExist()) {
      this.finalFirstPitch = new FinalFirstPitch(downPin);
      return;
    }
    if (!isSecondExist()) {
      this.finalSecondPitch = new FinalSecondPitch(this.finalFirstPitch, downPin);
      return;
    }
    if (!isBonusExist()) {
      this.finalBonusPitch = new FinalBonusPitch(this.finalFirstPitch, this.finalSecondPitch,
          downPin);
      return;
    }

  }

  private boolean isStrikeOrSpareUntilSecond() {
    return finalFirstPitch.getDownPin() + finalSecondPitch.getDownPin() >= BowlingGame.PIN_NUMBER;
  }

  private boolean isBonusExist() {
    return Optional.ofNullable(finalBonusPitch).isPresent();
  }

  private boolean isSecondExist() {
    return Optional.ofNullable(finalSecondPitch).isPresent();
  }

  private boolean isFirstExist() {
    return Optional.ofNullable(finalFirstPitch).isPresent();
  }

  @Override
  public boolean canPitch() {
    return !isFirstExist() || !isSecondExist() || (!isBonusExist() && isStrikeOrSpareUntilSecond());
  }

  @Override
  public int getRemainPin() {
    return remainPin;
  }

}
