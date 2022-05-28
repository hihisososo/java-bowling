package bowling.domain;

import static bowling.utl.RandomValueGenerator.getRandom;

public class NormalFrame implements Frame {

  private FirstPitch firstPitch;
  private SecondPitch secondPitch;

  public NormalFrame() {
    this.firstPitch = FirstPitch.EMPTY;
    this.secondPitch = SecondPitch.EMPTY;
  }

  @Override
  public void pitch() {
    if (firstPitch == FirstPitch.EMPTY) {
      doFirstPitch();
    }
    doSecondPitch();
  }

  private void doSecondPitch() {
    this.secondPitch = SecondPitch.from(firstPitch, getRandom(firstPitch.getDownPin()));
  }

  private void doFirstPitch() {
    this.firstPitch = FirstPitch.from(getRandom(BowlingGame.PIN_NUMBER));
    if (firstPitch == FirstPitch.STRIKE) {
      secondPitch = SecondPitch.from(firstPitch, 0);
    }
  }

  @Override
  public boolean needPitch() {
    return secondPitch == SecondPitch.EMPTY;
  }
}
