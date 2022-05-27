package bowling.domain;

import bowling.exception.PitchNotRecordException;
import java.util.Optional;

public class Score {

  private Stauts stauts;
  private FirstPitch firstPitch;
  private SecondPitch secondPitch;

  private Score(Stauts stauts) {
    this.stauts = stauts;
  }

  public static Score init() {
    return new Score(Stauts.EMPTY);
  }

  public Stauts getStatus() {
    return stauts;
  }

  public int getFirstKnockDownPin() {
    Optional.ofNullable(firstPitch).orElseThrow(() -> new PitchNotRecordException());
    return firstPitch.getKnockDownPin();
  }

  public int getSecondKnockDownPin() {
    Optional.ofNullable(secondPitch).orElseThrow(() -> new PitchNotRecordException());
    return secondPitch.getKnockDownPin();
  }

  public void record(FirstPitch firstPitch) {
    this.firstPitch = firstPitch;
    this.stauts = stauts.nextStatus(firstPitch);
  }

  public void record(SecondPitch secondPitch) {
    this.secondPitch = secondPitch;
    this.stauts = stauts.nextStatus(firstPitch, secondPitch);
  }

  public boolean isPlayable() {
    return this.stauts == Stauts.EMPTY || this.stauts == Stauts.NEED_SECOND_PITCH;
  }
}
